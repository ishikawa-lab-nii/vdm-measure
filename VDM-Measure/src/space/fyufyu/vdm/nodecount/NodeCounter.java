package space.fyufyu.vdm.nodecount;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import org.overture.ast.analysis.AnalysisException;
import org.overture.ast.definitions.PDefinition;
import org.overture.ast.lex.Dialect;
import org.overture.parser.lex.LexTokenReader;
import org.overture.parser.syntax.DefinitionReader;

import space.fyufyu.vdm.util.NodeType;
import space.fyufyu.vdm.util.VDMPPFilenameFilter;

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
	 * @param targetFile
	 *            Target file of node count (can be directory to run
	 *            recursively)
	 * @param reportFilePrefix
	 *            Prefix of the file path for the result files (results will
	 *            make $PREFIX-DEF.csv, $PREFIX-EXP.csv, $PREFIX-STM.csv)
	 * @throws AnalysisException
	 * @throws IOException
	 */
	public static void analyzeAndWrite(String targetFile,
			String reportFilePrefix) throws AnalysisException, IOException {
		LinkedList<NodeCountReport> reports = analyze(new File(targetFile));
		write(reports, reportFilePrefix, NodeType.DEF);
		write(reports, reportFilePrefix, NodeType.EXP);
		write(reports, reportFilePrefix, NodeType.STM);
	}

	/**
	 * Count nodes in VDM files
	 * 
	 * @param targetFile
	 *            Target file of node count (can be directory to run
	 *            recursively)
	 * @return
	 * @throws AnalysisException
	 */
	public static LinkedList<NodeCountReport> analyze(File targetFile)
			throws AnalysisException {
		System.out.println("Analyzing " + targetFile.getAbsolutePath());
		LinkedList<NodeCountReport> ret = new LinkedList<>();
		if (targetFile.isDirectory()) {
			File[] files = targetFile.listFiles();
			for (File file : files) {
				ret.addAll(analyze(new File(file.getAbsolutePath())));
			}
		} else {
			if (VDMPPFilenameFilter.isVDMPPFile(targetFile.getParentFile(),
					targetFile.getName())) {
				LexTokenReader reader = new LexTokenReader(targetFile, Dialect.VDM_PP);
				DefinitionReader defreader = new DefinitionReader(reader);
				List<PDefinition> nodes = defreader.readDefinitions();
				NodeCountVisitor visitor = new NodeCountVisitor(targetFile);
				visitor.run(nodes);
				ret.add(visitor.getReport());
			}
		}
		return ret;
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
	 * @throws IOException
	 */
	private static void write(LinkedList<NodeCountReport> reports,
			String reportFilePrefix, NodeType nodeType)
			throws IOException {
		String path = reportFilePrefix
				+ "-" + nodeType.toString() + ".csv";
		File f = new File(path);
		if(!f.getParentFile().exists()){
			f.getParentFile().mkdir();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(path));

		// Counter to remember the count sum for all the target files
		MapCounter totalCounter = new MapCounter();

		// TODO: refactor the following
		LinkedHashSet<String> values = new LinkedHashSet<String>();
		for (NodeCountReport report : reports) {
			MapCounter counter = report.getCounter(nodeType);
			values.addAll(counter.keySet());
		}

		bw.write(',');
		for (String val : values) {
			bw.write(val);
			bw.write(',');
		}
		bw.write('\n');

		for (NodeCountReport report : reports) {
			bw.write(report.getTargetFile().getAbsolutePath());
			bw.write(',');
			MapCounter counter = report.getCounter(nodeType);
			for (String val : values) {
				int count = counter.getValue(val);
				totalCounter.count(val, count);
				bw.write(Integer.toString(count));
				bw.write(',');
			}
			bw.write('\n');
		}

		bw.write("TOTAL,");
		for (String val : values) {
			int count = totalCounter.getValue(val);
			bw.write(Integer.toString(count));
			bw.write(',');
		}
		bw.write('\n');
		bw.write(',');
		for (String val : values) {
			bw.write(val);
			bw.write(',');
		}
		bw.write('\n');

		bw.close();
		System.out.println("Wrote the report to " + path);
	}

}
