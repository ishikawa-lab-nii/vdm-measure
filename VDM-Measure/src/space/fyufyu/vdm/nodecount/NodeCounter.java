package space.fyufyu.vdm.nodecount;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import org.overture.ast.analysis.AnalysisException;
import org.overture.ast.definitions.PDefinition;
import org.overture.ast.lex.Dialect;
import org.overture.parser.lex.LexTokenReader;
import org.overture.parser.syntax.DefinitionReader;

import space.fyufyu.vdm.util.NodeInfo;
import space.fyufyu.vdm.util.NodeType;

/**
 * Functions to count nodes of various kinds in AST and report in a a file
 * 
 * @author f-ishikawa
 *
 */
public class NodeCounter {

	/**
	 * Count nodes in VDM files and write the result in CSV files
	 * 
	 * @param commands
	 * @return Node count reports
	 * @throws AnalysisException
	 * @throws IOException
	 */
	public static NodeCountReport[] analyzeAndWriteAll(
			NodeCountCommand[] commands) throws AnalysisException, IOException {
		LinkedList<NodeCountReport> reports = new LinkedList<NodeCountReport>();
		for (int i = 0; i < commands.length; i++) {
			reports.add(analyzeAndWrite(commands[i]));
		}
		return reports.toArray(new NodeCountReport[] {});
	}

	/**
	 * Count nodes in VDM files and write the result in CSV files
	 * 
	 * @param command
	 * @return Node count report
	 * @throws AnalysisException
	 * @throws IOException
	 */
	public static NodeCountReport analyzeAndWrite(NodeCountCommand command)
			throws AnalysisException, IOException {
		return analyzeAndWrite(command.targetFileName,
				command.reportFilePrefix, command.filenameFilter,
				command.grouper);
	}

	/**
	 * Count nodes in VDM files and write the result in CSV files
	 * 
	 * @param targetFile
	 *            Target file of node count (can be directory to run
	 *            recursively)
	 * @param reportFilePrefix
	 *            Prefix of the file path for the result files (results will
	 *            make $PREFIX-DEF.csv, $PREFIX-EXP.csv, $PREFIX-STM.csv)
	 * @param filenameFilter
	 *            Filter to choose target files
	 * @param grouper
	 *            Mapping node name to a more meaningful/abstract name (group)
	 * @return Node count report
	 * @throws AnalysisException
	 * @throws IOException
	 */
	private static NodeCountReport analyzeAndWrite(String targetFile,
			String reportFilePrefix, FilenameFilter filenameFilter,
			NodeGrouper grouper) throws AnalysisException, IOException {
		NodeCountReport report = analyze(new File(targetFile), filenameFilter);
		if (report != null) {
			for (NodeType t : NodeInfo.ALL_NODE_TYPES) {
				write(report, reportFilePrefix, t, grouper);
			}
		}
		return report;
	}

	/**
	 * Count nodes in VDM files
	 * 
	 * @param targetFile
	 *            Target file of node count (can be directory to run
	 *            recursively)
	 * @param filenameFilter
	 *            Filter to choose target files
	 * @return Report (null if the file does not match the provided filter)
	 * @throws AnalysisException
	 */
	private static NodeCountReport analyze(File targetFile,
			FilenameFilter filenameFilter) throws AnalysisException,
			FileNotFoundException {
		if (!targetFile.exists()) {
			throw new FileNotFoundException(targetFile.getAbsolutePath());
		}
		System.out.println("Analyzing " + targetFile.getAbsolutePath());
		if (targetFile.isDirectory()) {
			File[] files = targetFile.listFiles();
			LinkedList<NodeCountReport> children = new LinkedList<>();
			for (File file : files) {
				NodeCountReport r = analyze(new File(file.getAbsolutePath()),
						filenameFilter);
				if (r != null) {
					children.add(r);
				}
			}
			return new NodeCountReport(targetFile, children);
		} else {
			if (filenameFilter.accept(targetFile.getParentFile(),
					targetFile.getName())) {
				LexTokenReader reader = new LexTokenReader(targetFile,
						Dialect.VDM_PP);
				DefinitionReader defreader = new DefinitionReader(reader);
				List<PDefinition> nodes = defreader.readDefinitions();
				NodeCountVisitor visitor = new NodeCountVisitor(targetFile);
				visitor.run(nodes);
				return visitor.getReport();
			} else {
				return null;
			}
		}
	}

	/**
	 * Write result of node count to CSV files
	 * 
	 * @param reports
	 *            List of reports for target files
	 * @param reportFilePrefix
	 *            Prefix of the file path for the result files (results will
	 *            make $PREFIX-$TYPE.csv)
	 * @param nodeType
	 *            Type of the nodes (DEF, EXP or STM)
	 * @param grouper
	 *            Function to define mapping from raw class names to meaningful
	 *            names for node types
	 * @throws IOException
	 */
	private static void write(NodeCountReport report, String reportFilePrefix,
			NodeType nodeType, NodeGrouper grouper) throws IOException {
		String path = reportFilePrefix + "-" + nodeType.toString() + ".csv";
		File f = new File(path);
		if (!f.getParentFile().exists()) {
			f.getParentFile().mkdir();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(path));
		MapCounter rootCounter = report.getCounter(nodeType);
		rootCounter = grouper.apply(rootCounter);

		LinkedHashSet<String> values = new LinkedHashSet<String>(DefaultNodeGrouper.DEFAULT_GROUPING.get(nodeType).values());

		// Header row
		bw.write(',');
		for (String val : values) {
			bw.write(val);
			bw.write(",,");
		}
		bw.write('\n');

		// Total
		bw.write("TOTAL,");
		for (String val : values) {
			int count = rootCounter.getValue(val);
			bw.write(Integer.toString(count));
			bw.write(',');
			bw.write(String.format("%.3f", 100.0 * count / rootCounter.getSum()));
			bw.write(',');
		}
		bw.write('\n');

		// Each file
		for (NodeCountReport child : report.getChildren()) {
			writeSub(child, bw, values, nodeType, report.getTargetFile()
					.getAbsolutePath().length(), grouper);
		}

		bw.close();
		System.out.println("Wrote the report to " + path);
	}

	private static void writeSub(NodeCountReport report, Writer wr,
			LinkedHashSet<String> values, NodeType nodeType, int prefixLength,
			NodeGrouper grouper) throws IOException {
		if (report.getTargetFile().isDirectory()) {
			wr.write("[DIR]");
		}
		wr.write(report.getTargetFile().getAbsolutePath()
				.substring(prefixLength));
		wr.write(',');
		MapCounter counter = grouper.apply(report.getCounter(nodeType));
		for (String val : values) {
			int count = counter.getValue(val);
			wr.write(Integer.toString(count));
			wr.write(',');
			if (counter.getSum() == 0) {
				wr.write("N/A");
			} else {
				wr.write(String.format("%.3f", 100.0 * count / counter.getSum()));
			}
			wr.write(',');
		}
		wr.write('\n');
		for (NodeCountReport child : report.getChildren()) {
			writeSub(child, wr, values, nodeType, prefixLength, grouper);
		}
	}

}
