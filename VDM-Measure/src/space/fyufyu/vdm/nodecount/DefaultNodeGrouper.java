package space.fyufyu.vdm.nodecount;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Default node grouping
 * 
 * @author f-ishikawa
 *
 */
public class DefaultNodeGrouper extends NodeGrouper {

	static Map<String, String> DEFAULT_GROUPING;
	static {
		DEFAULT_GROUPING = new LinkedHashMap<String, String>();

		// Definition nodes
		DEFAULT_GROUPING.put("ATypeDefinition", "1. Type Def.");
		DEFAULT_GROUPING.put("AValueDefinition", "2. Value Def.");
		DEFAULT_GROUPING
				.put("AInstanceVariableDefinition", "3A. Variable Def.");
		DEFAULT_GROUPING.put("AClassInvariantDefinition", "3B. Invariant Def.");
		DEFAULT_GROUPING.put("AExplicitFunctionDefinition",
				"4A. Explicit Function Def.");
		DEFAULT_GROUPING.put("AExplicitOperationDefinition",
				"5A. Explicit Operation Def.");

		DEFAULT_GROUPING.put("AForAllExp", "Quantifier Exp.");
		DEFAULT_GROUPING.put("AExistsExp", "Quantifier Exp.");
		DEFAULT_GROUPING.put("AExists1Exp", "Quantifier Exp.");
		DEFAULT_GROUPING.put("AIotaExp", "Quantifier Exp.");

		DEFAULT_GROUPING.put("ALetBeStExp", "Let Be Exp.");
		
		DEFAULT_GROUPING.put("AEquivalentBooleanBinaryExp",
				"Logic Operator Exp.");
		DEFAULT_GROUPING.put("AImpliesBooleanBinaryExp", "Logic Operator Exp.");
		DEFAULT_GROUPING.put("AAndBooleanBinaryExp", "Logic Operator Exp.");
		DEFAULT_GROUPING.put("AOrBooleanBinaryExp", "Logic Operator Exp.");
		DEFAULT_GROUPING.put("ANotUnaryExp", "Logic Operator Exp.");

		DEFAULT_GROUPING.put("AEqualsBinaryExp", "Basic Exp.");
		DEFAULT_GROUPING.put("ANotEqualBinaryExp", "Basic Exp.");
		DEFAULT_GROUPING.put("ANewExp", "Basic Exp.");
		DEFAULT_GROUPING.put("AIsExp", "Basic Exp.");
		DEFAULT_GROUPING.put("AVariableExp", "Basic Exp.");
		DEFAULT_GROUPING.put("AApplyExp", "Basic Exp.");
		DEFAULT_GROUPING.put("AFieldExp", "Basic Exp.");
		DEFAULT_GROUPING.put("AFieldNumberExp", "Basic Exp.");
		DEFAULT_GROUPING.put("AMkTypeExp", "Basic Exp.");
		DEFAULT_GROUPING.put("ATupleExp", "Basic Exp.");
		DEFAULT_GROUPING.put("ASelfExp", "Basic Exp.");

		DEFAULT_GROUPING.put("ASetCompSetExp", "Set/Seq/Map Intension Exp.");
		DEFAULT_GROUPING.put("AMapCompMapExp", "Set/Seq/Map Intension Exp.");

		DEFAULT_GROUPING.put("ACardinalityUnaryExp",
				"Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("ASetUnionBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("ASetIntersectBinaryExp",
				"Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("ASetDifferenceBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("ASubsetBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("AInSetBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("ANotInSetBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("ADistUnionUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING
				.put("ASeqConcatBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("AElementsUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("ASeqCompSeqExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("AIndicesUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("ASubseqExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("ALenUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("AMuExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("AReverseUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("ADistConcatUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("AHeadUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("ATailUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("AMapDomainUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("AMapUnionBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("APlusPlusBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("AMapletExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("AMapRangeUnaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("ADomainResToBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("ADomainResByBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("ARangeResToBinaryExp", "Set/Seq/Map Operator Exp.");
		DEFAULT_GROUPING.put("ARangeResByBinaryExp", "Set/Seq/Map Operator Exp.");

		DEFAULT_GROUPING.put("AGreaterNumericBinaryExp",
				"Numeric Operator Exp.");
		DEFAULT_GROUPING.put("AGreaterEqualNumericBinaryExp",
				"Numeric Operator Exp.");
		DEFAULT_GROUPING.put("ALessNumericBinaryExp", "Numeric Operator Exp.");
		DEFAULT_GROUPING.put("ALessEqualNumericBinaryExp",
				"Numeric Operator Exp.");
		DEFAULT_GROUPING.put("APlusNumericBinaryExp", "Numeric Operator Exp.");
		DEFAULT_GROUPING.put("ASubtractNumericBinaryExp",
				"Numeric Operator Exp.");
		DEFAULT_GROUPING.put("ATimesNumericBinaryExp", "Numeric Operator Exp.");
		DEFAULT_GROUPING.put("ADivNumericBinaryExp", "Numeric Operator Exp.");
		DEFAULT_GROUPING
				.put("ADivideNumericBinaryExp", "Numeric Operator Exp.");
		DEFAULT_GROUPING.put("AModNumericBinaryExp", "Numeric Operator Exp.");
		DEFAULT_GROUPING.put("AUnaryMinusUnaryExp", "Numeric Operator Exp.");
		DEFAULT_GROUPING.put("AStarStarBinaryExp", "Numeric Operator Exp.");

		DEFAULT_GROUPING.put("AIntLiteralExp", "Literal Exp.");
		DEFAULT_GROUPING.put("ARealLiteralExp", "Literal Exp.");
		DEFAULT_GROUPING.put("AStringLiteralExp", "Literal Exp.");
		DEFAULT_GROUPING.put("AQuoteLiteralExp", "Literal Exp.");
		DEFAULT_GROUPING.put("ANilExp", "Literal Exp.");
		DEFAULT_GROUPING.put("ABooleanConstExp", "Literal Exp.");
		DEFAULT_GROUPING.put("ACharLiteralExp", "Literal Exp.");
		DEFAULT_GROUPING.put("ASetEnumSetExp", "Literal Exp.");
		DEFAULT_GROUPING.put("ASetRangeSetExp", "Literal Exp.");
		DEFAULT_GROUPING.put("ASeqEnumSeqExp", "Literal Exp.");
		DEFAULT_GROUPING.put("AMapEnumMapExp", "Literal Exp.");

		DEFAULT_GROUPING.put("AIfExp", "If Exp.");
		DEFAULT_GROUPING.put("AElseIfExp", "If Exp.");
		DEFAULT_GROUPING.put("ACasesExp", "Case Exp.");
		
		DEFAULT_GROUPING.put("ALambdaExp", "Lambda Exp.");
		DEFAULT_GROUPING.put("AFuncInstatiationExp", "Function Type Instantiation Exp.");
		
		DEFAULT_GROUPING.put("AUndefinedExp", "Undefined Exp.");
		
		// Statements nodes
		DEFAULT_GROUPING.put("AReturnStm", "Atomic Stm.");
		DEFAULT_GROUPING.put("AAssignmentStm", "Assignment Stm.");
		DEFAULT_GROUPING.put("ABlockSimpleBlockStm", "Block Stm.");
		DEFAULT_GROUPING.put("ACallObjectStm", "Object Call Stm.");
		DEFAULT_GROUPING.put("ACallStm", "Call Stm.");
		
		DEFAULT_GROUPING.put("AIfStm", "If Stm.");
		DEFAULT_GROUPING.put("AElseIfStm", "If Stm.");
		DEFAULT_GROUPING.put("ACasesStm", "Case Stm.");
		DEFAULT_GROUPING.put("AForIndexStm", "For Stm.");
		DEFAULT_GROUPING.put("AForAllStm", "For Stm.");
		DEFAULT_GROUPING.put("AForPatternBindStm", "For Stm.");
		DEFAULT_GROUPING.put("AWhileStm", "While Stm.");
		DEFAULT_GROUPING.put("ATrapStm", "Tixe Stm.");
		DEFAULT_GROUPING.put("ATixeStm", "Tixe Stm.");

		DEFAULT_GROUPING.put("ALetDefExp", "Let/Def Exp.");
		DEFAULT_GROUPING.put("ADefExp", "Let/Def Exp.");
		DEFAULT_GROUPING.put("ALetStm", "Let/Def Stm.");

		DEFAULT_GROUPING.put("ANotYetSpecifiedExp", "Not Yet Exp.");
		DEFAULT_GROUPING.put("ANotYetSpecifiedStm", "Not Yet Stm.");
		DEFAULT_GROUPING.put("ASubclassResponsibilityExp", "Subclass Responsibility Exp.");
		DEFAULT_GROUPING.put("ASubclassResponsibilityStm", "Subclass Responsibility Stm.");
		DEFAULT_GROUPING.put("ASkipStm", "Skip Stm.");

		DEFAULT_GROUPING.put("AExitStm", "Exit Stm.");
		DEFAULT_GROUPING.put("AErrorStm", "Error Stm.");
	}

	@Override
	public String map(String nodeName) {
		if (DEFAULT_GROUPING.containsKey(nodeName)) {
			return DEFAULT_GROUPING.get(nodeName);
		}
		throw new RuntimeException(nodeName);
	}

}
