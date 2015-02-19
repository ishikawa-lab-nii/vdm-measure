package space.fyufyu.vdm.nodecount;

import java.io.File;
import java.util.List;

import org.overture.ast.analysis.AnalysisException;
import org.overture.ast.analysis.DepthFirstAnalysisAdaptor;
import org.overture.ast.definitions.PDefinition;
import org.overture.ast.expressions.PExp;
import org.overture.ast.statements.PStm;

import space.fyufyu.vdm.util.NodeType;

/**
 * AST Visitor to count nodes 
 * 
 * @author f-ishikawa
 *
 */
class NodeCountVisitor extends DepthFirstAnalysisAdaptor {

	private NodeCountReport report;

	NodeCountVisitor(File targetFile) {
		report = new NodeCountReport(targetFile);
	}

	NodeCountReport getReport() {
		return report;
	}
	
	void run(List<PDefinition> nodes) throws AnalysisException{
		for (PDefinition node : nodes) {
			report.count(NodeType.DEF, node);
			node.apply(this);
		}
	}

	@Override
	public void defaultInPExp(PExp node) throws AnalysisException {
		super.defaultInPExp(node);
		report.count(NodeType.EXP, node);
	}

	@Override
	public void defaultInPStm(PStm node) throws AnalysisException {
		super.defaultInPStm(node);
		report.count(NodeType.STM, node);
	}

}