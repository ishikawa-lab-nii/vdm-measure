package space.fyufyu.vdm.nodecount;

import java.io.File;
import java.util.HashMap;

import org.overture.ast.node.INode;

import space.fyufyu.vdm.util.NodeType;

/**
 * Report of node count for each file
 * 
 * @author f-ishikawa
 *
 */
public class NodeCountReport {

	private File targetFile;

	/* Remember the count for each node type */
	private HashMap<NodeType, MapCounter> counters = new HashMap<NodeType, MapCounter>();

	NodeCountReport(File targetFile) {
		this.targetFile = targetFile;
		counters.put(NodeType.DEF, new MapCounter());
		counters.put(NodeType.EXP, new MapCounter());
		counters.put(NodeType.STM, new MapCounter());
	}

	File getTargetFile() {
		return targetFile;
	}

	MapCounter getCounter(NodeType nodeType) {
		return counters.get(nodeType);
	}

	void count(NodeType nodeType, INode node) {
		String nodeString = node.getClass().getSimpleName();
		counters.get(nodeType).count(nodeString);
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Report on file: ");
		buf.append(targetFile);
		buf.append("\nDefinition Count: ");
		buf.append(counters.get(NodeType.DEF).toString());
		buf.append("\nExpression Count: ");
		buf.append(counters.get(NodeType.EXP).toString());
		buf.append("\nStatement Count: ");
		buf.append(counters.get(NodeType.STM).toString());
		return buf.toString();
	}

}
