package space.fyufyu.vdm.nodecount;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.overture.ast.node.INode;

import space.fyufyu.vdm.util.NodeInfo;
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

	private List<NodeCountReport> children = new LinkedList<>();

	private boolean isForDirectory = false;

	/**
	 * Constructor for a single file case
	 * 
	 * @param targetFile
	 */
	NodeCountReport(File targetFile) {
		this.targetFile = targetFile;
		for (NodeType t : NodeInfo.ALL_NODE_TYPES) {
			counters.put(t, new MapCounter());
		}
	}

	/**
	 * Constructor for a directory case
	 * 
	 * @param targetFile
	 * @param children
	 */
	NodeCountReport(File targetFile, List<NodeCountReport> children) {
		this.targetFile = targetFile;
		for (NodeCountReport child : children) {
			if (!child.isEmpty()) {
				this.children.add(child);
			}
		}
		this.isForDirectory = true;

		for (NodeType t : NodeInfo.ALL_NODE_TYPES) {
			MapCounter counter = new MapCounter();
			for (NodeCountReport report : this.children) {
				counter.merge(report.getCounter(t));
			}
			counters.put(t, counter);
		}
	}

	File getTargetFile() {
		return targetFile;
	}

	MapCounter getCounter(NodeType nodeType) {
		return counters.get(nodeType);
	}

	void count(NodeType nodeType, INode node) {
		String nodeString = node.getClass().getSimpleName();
		if (!DefaultNodeGrouper.DEFAULT_GROUPING.containsKey(nodeString)) {
			System.err.println("Node not yet registered: " + nodeString + ":\n" + node.toString());
			throw new RuntimeException();
		}
		counters.get(nodeType).count(nodeString);
	}

	List<NodeCountReport> getChildren() {
		return children;
	}

	boolean isForDirectory() {
		return isForDirectory;
	}

	boolean isEmpty() {
		for (NodeType t : NodeInfo.ALL_NODE_TYPES) {
			MapCounter counter = counters.get(t);
			if (!counter.isEmpty()) {
				return false;
			}
		}
		for (NodeCountReport child : children) {
			if (!child.isEmpty()) {
				return false;
			}
		}
		return true;
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
