package space.fyufyu.vdm.nodecount;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import space.fyufyu.vdm.util.NodeType;

/**
 * Default node grouping
 * 
 * @author f-ishikawa
 *
 */
public class DefaultNodeGrouper extends NodeGrouper {

	public static Map<NodeType, Map<String, String>> DEFAULT_GROUPING;
	
	static HashSet<String> knownNodeClasses;
	
	static {
		// Definition nodes
		LinkedHashMap<String, String> DEFAULT_GROUPING_DEF = new LinkedHashMap<String, String>();
		DEFAULT_GROUPING_DEF.put("ATypeDefinition", "1. Type Def.");
		DEFAULT_GROUPING_DEF.put("AValueDefinition", "2. Value Def.");
		DEFAULT_GROUPING_DEF
				.put("AInstanceVariableDefinition", "3A. Variable Def.");
		DEFAULT_GROUPING_DEF.put("AClassInvariantDefinition", "3B. Invariant Def.");
		DEFAULT_GROUPING_DEF.put("AExplicitFunctionDefinition",
				"4A. Explicit Function Def.");
		DEFAULT_GROUPING_DEF.put("AExplicitOperationDefinition",
				"5A. Explicit Operation Def.");

		// Expression nodes
		LinkedHashMap<String, String> DEFAULT_GROUPING_EXP = new LinkedHashMap<String, String>();
		DEFAULT_GROUPING_EXP.put("AForAllExp", "Quantifier Exp.");
		DEFAULT_GROUPING_EXP.put("AExistsExp", "Quantifier Exp.");
		DEFAULT_GROUPING_EXP.put("AExists1Exp", "Quantifier Exp.");
		DEFAULT_GROUPING_EXP.put("AIotaExp", "Quantifier Exp.");

		DEFAULT_GROUPING_EXP.put("ALetBeStExp", "Let Be Exp.");
		
		DEFAULT_GROUPING_EXP.put("AEquivalentBooleanBinaryExp",
				"Logic Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AImpliesBooleanBinaryExp", "Logic Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AAndBooleanBinaryExp", "Logic Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AOrBooleanBinaryExp", "Logic Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ANotUnaryExp", "Logic Operator Exp.");

		DEFAULT_GROUPING_EXP.put("AEqualsBinaryExp", "Basic Exp.");
		DEFAULT_GROUPING_EXP.put("ANotEqualBinaryExp", "Basic Exp.");
		DEFAULT_GROUPING_EXP.put("ANewExp", "Basic Exp.");
		DEFAULT_GROUPING_EXP.put("AIsExp", "Basic Exp.");
		DEFAULT_GROUPING_EXP.put("AVariableExp", "Basic Exp.");
		DEFAULT_GROUPING_EXP.put("AApplyExp", "Basic Exp.");
		DEFAULT_GROUPING_EXP.put("AFieldExp", "Basic Exp.");
		DEFAULT_GROUPING_EXP.put("AFieldNumberExp", "Basic Exp.");
		DEFAULT_GROUPING_EXP.put("AMkTypeExp", "Basic Exp.");
		DEFAULT_GROUPING_EXP.put("ATupleExp", "Basic Exp.");
		DEFAULT_GROUPING_EXP.put("ASelfExp", "Basic Exp.");

		DEFAULT_GROUPING_EXP.put("ASetCompSetExp", "Set/Seq/Map Intension Exp.");
		DEFAULT_GROUPING_EXP.put("AMapCompMapExp", "Set/Seq/Map Intension Exp.");

		DEFAULT_GROUPING_EXP.put("ACardinalityUnaryExp",
				"Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ASetUnionBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ASetIntersectBinaryExp",
				"Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ASetDifferenceBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ASubsetBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AInSetBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ANotInSetBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ADistUnionUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP
				.put("ASeqConcatBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AElementsUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ASeqCompSeqExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AIndicesUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ASubseqExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ALenUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AMuExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AReverseUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ADistConcatUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AHeadUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ATailUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AMapDomainUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AMapUnionBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("APlusPlusBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AMapletExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AMapRangeUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ADomainResToBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ADomainResByBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ARangeResToBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ARangeResByBinaryExp", "Set/Seq/Map Operator Exp.");

		DEFAULT_GROUPING_EXP.put("AGreaterNumericBinaryExp",
				"Numeric Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AGreaterEqualNumericBinaryExp",
				"Numeric Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ALessNumericBinaryExp", "Numeric Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ALessEqualNumericBinaryExp",
				"Numeric Operator Exp.");
		DEFAULT_GROUPING_EXP.put("APlusNumericBinaryExp", "Numeric Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ASubtractNumericBinaryExp",
				"Numeric Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ATimesNumericBinaryExp", "Numeric Operator Exp.");
		DEFAULT_GROUPING_EXP.put("ADivNumericBinaryExp", "Numeric Operator Exp.");
		DEFAULT_GROUPING_EXP
				.put("ADivideNumericBinaryExp", "Numeric Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AModNumericBinaryExp", "Numeric Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AUnaryMinusUnaryExp", "Numeric Operator Exp.");
		DEFAULT_GROUPING_EXP.put("AStarStarBinaryExp", "Numeric Operator Exp.");

		DEFAULT_GROUPING_EXP.put("AIntLiteralExp", "Literal Exp.");
		DEFAULT_GROUPING_EXP.put("ARealLiteralExp", "Literal Exp.");
		DEFAULT_GROUPING_EXP.put("AStringLiteralExp", "Literal Exp.");
		DEFAULT_GROUPING_EXP.put("AQuoteLiteralExp", "Literal Exp.");
		DEFAULT_GROUPING_EXP.put("ANilExp", "Literal Exp.");
		DEFAULT_GROUPING_EXP.put("ABooleanConstExp", "Literal Exp.");
		DEFAULT_GROUPING_EXP.put("ACharLiteralExp", "Literal Exp.");
		DEFAULT_GROUPING_EXP.put("ASetEnumSetExp", "Literal Exp.");
		DEFAULT_GROUPING_EXP.put("ASetRangeSetExp", "Literal Exp.");
		DEFAULT_GROUPING_EXP.put("ASeqEnumSeqExp", "Literal Exp.");
		DEFAULT_GROUPING_EXP.put("AMapEnumMapExp", "Literal Exp.");

		DEFAULT_GROUPING_EXP.put("AIfExp", "If Exp.");
		DEFAULT_GROUPING_EXP.put("AElseIfExp", "If Exp.");
		DEFAULT_GROUPING_EXP.put("ACasesExp", "Case Exp.");
		
		DEFAULT_GROUPING_EXP.put("ALambdaExp", "Lambda Exp.");
		DEFAULT_GROUPING_EXP.put("AFuncInstatiationExp", "Function Type Instantiation Exp.");
		
		DEFAULT_GROUPING_EXP.put("AUndefinedExp", "Undefined Exp.");
		
		// Statement nodes
		LinkedHashMap<String, String> DEFAULT_GROUPING_STM = new LinkedHashMap<String, String>();

		DEFAULT_GROUPING_STM.put("AReturnStm", "Atomic Stm.");
		DEFAULT_GROUPING_STM.put("AAssignmentStm", "Assignment Stm.");
		DEFAULT_GROUPING_STM.put("ABlockSimpleBlockStm", "Block Stm.");
		DEFAULT_GROUPING_STM.put("ACallObjectStm", "Object Call Stm.");
		DEFAULT_GROUPING_STM.put("ACallStm", "Call Stm.");
		
		DEFAULT_GROUPING_STM.put("AIfStm", "If Stm.");
		DEFAULT_GROUPING_STM.put("AElseIfStm", "If Stm.");
		DEFAULT_GROUPING_STM.put("ACasesStm", "Case Stm.");
		DEFAULT_GROUPING_STM.put("AForIndexStm", "For Stm.");
		DEFAULT_GROUPING_STM.put("AForAllStm", "For Stm.");
		DEFAULT_GROUPING_STM.put("AForPatternBindStm", "For Stm.");
		DEFAULT_GROUPING_STM.put("AWhileStm", "While Stm.");
		DEFAULT_GROUPING_STM.put("ATrapStm", "Tixe Stm.");
		DEFAULT_GROUPING_STM.put("ATixeStm", "Tixe Stm.");

		DEFAULT_GROUPING_STM.put("ALetDefExp", "Let/Def Exp.");
		DEFAULT_GROUPING_STM.put("ADefExp", "Let/Def Exp.");
		DEFAULT_GROUPING_STM.put("ALetStm", "Let/Def Stm.");

		DEFAULT_GROUPING_STM.put("ANotYetSpecifiedExp", "Not Yet Exp.");
		DEFAULT_GROUPING_STM.put("ANotYetSpecifiedStm", "Not Yet Stm.");
		DEFAULT_GROUPING_STM.put("ASubclassResponsibilityExp", "Subclass Responsibility Exp.");
		DEFAULT_GROUPING_STM.put("ASubclassResponsibilityStm", "Subclass Responsibility Stm.");
		DEFAULT_GROUPING_STM.put("ASkipStm", "Skip Stm.");

		DEFAULT_GROUPING_STM.put("AExitStm", "Exit Stm.");
		DEFAULT_GROUPING_STM.put("AErrorStm", "Error Stm.");
		
		DEFAULT_GROUPING = new HashMap<NodeType, Map<String, String>>();
		DEFAULT_GROUPING.put(NodeType.DEF, DEFAULT_GROUPING_DEF);
		DEFAULT_GROUPING.put(NodeType.EXP, DEFAULT_GROUPING_EXP);
		DEFAULT_GROUPING.put(NodeType.STM, DEFAULT_GROUPING_STM);
		knownNodeClasses = new HashSet<String>();
		knownNodeClasses.addAll(DEFAULT_GROUPING_DEF.keySet());
		knownNodeClasses.addAll(DEFAULT_GROUPING_EXP.keySet());
		knownNodeClasses.addAll(DEFAULT_GROUPING_STM.keySet());
	}

	@Override
	public String map(String nodeName) {
		String ret = DEFAULT_GROUPING.get(NodeType.DEF).get(nodeName);
		if(ret != null){
			return ret;
		}
		ret = DEFAULT_GROUPING.get(NodeType.EXP).get(nodeName);
		if(ret != null){
			return ret;
		}
		ret = DEFAULT_GROUPING.get(NodeType.STM).get(nodeName);
		if(ret != null){
			return ret;
		}
		throw new RuntimeException(nodeName);
	}

}
