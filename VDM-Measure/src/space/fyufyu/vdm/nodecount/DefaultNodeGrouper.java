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

	private static String BASIC_EXP = "1. Basic Exp.";
	private static String LITERAL_EXP =  "2. Literal Exp.";
	private static String NUMERICOP_EXP = "3. Numeric Operator Exp.";
	private static String LOGICOP_EXP = "4. Logic Operator Exp.";
	private static String ABSTRACTDATAOP_EXP = "5. Set/Seq/Map Operator Exp.";
	private static String DECLARATIVE_EXP = "6. Declarative Exp.";
	private static String CONDITIONAL_EXP = "7. Conditional Exp.";
	private static String FUNCTIONAL_EXP = "8. Functional Exp.";
	private static String LET_EXP = "9. Let/Def Exp.";
	private static String NOTSPEC_EXP = "10. Not Yet Exp.";
	private static String UNDEFINED_EXP = "11. Undefined Exp.";
	
	private static String BASIC_STM = "1. Basic Stm.";
	private static String CONDITIONAL_STM = "2. Conditional Stm.";
	private static String LOOP_STM = "3. Loop Stm.";
	private static String ERRORHANDING_STM = "4. Error Handling Stm.";
	private static String LET_STM = "5. Let/Def Stm.";
	private static String NOTSPEC_STM = "6. Not Yet Stm.";
	
	
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
		DEFAULT_GROUPING_EXP.put("AEqualsBinaryExp", BASIC_EXP);
		DEFAULT_GROUPING_EXP.put("ANotEqualBinaryExp", BASIC_EXP);
		DEFAULT_GROUPING_EXP.put("ANewExp", BASIC_EXP);
		DEFAULT_GROUPING_EXP.put("AIsExp", BASIC_EXP);
		DEFAULT_GROUPING_EXP.put("AVariableExp", BASIC_EXP);
		DEFAULT_GROUPING_EXP.put("AApplyExp", BASIC_EXP);
		DEFAULT_GROUPING_EXP.put("AFieldExp", BASIC_EXP);
		DEFAULT_GROUPING_EXP.put("AFieldNumberExp", BASIC_EXP);
		DEFAULT_GROUPING_EXP.put("AMkTypeExp", BASIC_EXP);
		DEFAULT_GROUPING_EXP.put("ATupleExp", BASIC_EXP);
		DEFAULT_GROUPING_EXP.put("ASelfExp", BASIC_EXP);
		
		DEFAULT_GROUPING_EXP.put("AIntLiteralExp", LITERAL_EXP);
		DEFAULT_GROUPING_EXP.put("ARealLiteralExp", LITERAL_EXP);
		DEFAULT_GROUPING_EXP.put("AStringLiteralExp", LITERAL_EXP);
		DEFAULT_GROUPING_EXP.put("AQuoteLiteralExp", LITERAL_EXP);
		DEFAULT_GROUPING_EXP.put("ANilExp", LITERAL_EXP);
		DEFAULT_GROUPING_EXP.put("ABooleanConstExp", LITERAL_EXP);
		DEFAULT_GROUPING_EXP.put("ACharLiteralExp", LITERAL_EXP);
		DEFAULT_GROUPING_EXP.put("ASetEnumSetExp", LITERAL_EXP);
		DEFAULT_GROUPING_EXP.put("ASetRangeSetExp", LITERAL_EXP);
		DEFAULT_GROUPING_EXP.put("ASeqEnumSeqExp", LITERAL_EXP);
		DEFAULT_GROUPING_EXP.put("AMapEnumMapExp", LITERAL_EXP);

		DEFAULT_GROUPING_EXP.put("AGreaterNumericBinaryExp",
				NUMERICOP_EXP);
		DEFAULT_GROUPING_EXP.put("AGreaterEqualNumericBinaryExp",
				NUMERICOP_EXP);
		DEFAULT_GROUPING_EXP.put("ALessNumericBinaryExp", NUMERICOP_EXP);
		DEFAULT_GROUPING_EXP.put("ALessEqualNumericBinaryExp",
				NUMERICOP_EXP);
		DEFAULT_GROUPING_EXP.put("APlusNumericBinaryExp", NUMERICOP_EXP);
		DEFAULT_GROUPING_EXP.put("ASubtractNumericBinaryExp",
				NUMERICOP_EXP);
		DEFAULT_GROUPING_EXP.put("ATimesNumericBinaryExp", NUMERICOP_EXP);
		DEFAULT_GROUPING_EXP.put("ADivNumericBinaryExp", NUMERICOP_EXP);
		DEFAULT_GROUPING_EXP
				.put("ADivideNumericBinaryExp", NUMERICOP_EXP);
		DEFAULT_GROUPING_EXP.put("AModNumericBinaryExp", NUMERICOP_EXP);
		DEFAULT_GROUPING_EXP.put("AUnaryMinusUnaryExp", NUMERICOP_EXP);
		DEFAULT_GROUPING_EXP.put("AStarStarBinaryExp", NUMERICOP_EXP);

		DEFAULT_GROUPING_EXP.put("AEquivalentBooleanBinaryExp",
				LOGICOP_EXP);
		DEFAULT_GROUPING_EXP.put("AImpliesBooleanBinaryExp", LOGICOP_EXP);
		DEFAULT_GROUPING_EXP.put("AAndBooleanBinaryExp", LOGICOP_EXP);
		DEFAULT_GROUPING_EXP.put("AOrBooleanBinaryExp", LOGICOP_EXP);
		DEFAULT_GROUPING_EXP.put("ANotUnaryExp", LOGICOP_EXP);
		
		DEFAULT_GROUPING_EXP.put("ACardinalityUnaryExp",
				ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("ASetUnionBinaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("ASetIntersectBinaryExp",
				ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("ASetDifferenceBinaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("ASubsetBinaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("AInSetBinaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("ANotInSetBinaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("ADistUnionUnaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP
				.put("ASeqConcatBinaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("AElementsUnaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("ASeqCompSeqExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("AIndicesUnaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("ASubseqExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("ALenUnaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("AMuExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("AReverseUnaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("ADistConcatUnaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("AHeadUnaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("ATailUnaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("AMapDomainUnaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("AMapUnionBinaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("APlusPlusBinaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("AMapletExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("AMapRangeUnaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("ADomainResToBinaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("ADomainResByBinaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("ARangeResToBinaryExp", ABSTRACTDATAOP_EXP);
		DEFAULT_GROUPING_EXP.put("ARangeResByBinaryExp", ABSTRACTDATAOP_EXP);

		DEFAULT_GROUPING_EXP.put("ASetCompSetExp", DECLARATIVE_EXP);
		DEFAULT_GROUPING_EXP.put("AMapCompMapExp", DECLARATIVE_EXP);
		DEFAULT_GROUPING_EXP.put("AForAllExp", DECLARATIVE_EXP);
		DEFAULT_GROUPING_EXP.put("AExistsExp", DECLARATIVE_EXP);
		DEFAULT_GROUPING_EXP.put("AExists1Exp", DECLARATIVE_EXP);
		DEFAULT_GROUPING_EXP.put("AIotaExp", DECLARATIVE_EXP);
		DEFAULT_GROUPING_EXP.put("ALetBeStExp", DECLARATIVE_EXP);

		DEFAULT_GROUPING_EXP.put("AIfExp", CONDITIONAL_EXP);
		DEFAULT_GROUPING_EXP.put("AElseIfExp", CONDITIONAL_EXP);
		DEFAULT_GROUPING_EXP.put("ACasesExp", CONDITIONAL_EXP);
		
		DEFAULT_GROUPING_EXP.put("ALambdaExp", FUNCTIONAL_EXP);
		DEFAULT_GROUPING_EXP.put("AFuncInstatiationExp", FUNCTIONAL_EXP);

		DEFAULT_GROUPING_EXP.put("ALetDefExp", LET_EXP);
		DEFAULT_GROUPING_EXP.put("ADefExp", LET_EXP);

		DEFAULT_GROUPING_EXP.put("ANotYetSpecifiedExp", NOTSPEC_EXP);
		DEFAULT_GROUPING_EXP.put("ASubclassResponsibilityExp", NOTSPEC_EXP);

		DEFAULT_GROUPING_EXP.put("AUndefinedExp", UNDEFINED_EXP);
		
		// Statement nodes
		LinkedHashMap<String, String> DEFAULT_GROUPING_STM = new LinkedHashMap<String, String>();

		DEFAULT_GROUPING_STM.put("AReturnStm", BASIC_STM);
		DEFAULT_GROUPING_STM.put("AAssignmentStm", BASIC_STM);
		DEFAULT_GROUPING_STM.put("ABlockSimpleBlockStm", BASIC_STM);
		DEFAULT_GROUPING_STM.put("ACallObjectStm", BASIC_STM);
		DEFAULT_GROUPING_STM.put("ACallStm", BASIC_STM);
		
		DEFAULT_GROUPING_STM.put("AIfStm", CONDITIONAL_STM);
		DEFAULT_GROUPING_STM.put("AElseIfStm", CONDITIONAL_STM);
		DEFAULT_GROUPING_STM.put("ACasesStm", CONDITIONAL_STM);
		
		DEFAULT_GROUPING_STM.put("AForIndexStm", LOOP_STM);
		DEFAULT_GROUPING_STM.put("AForAllStm", LOOP_STM);
		DEFAULT_GROUPING_STM.put("AForPatternBindStm",LOOP_STM);
		DEFAULT_GROUPING_STM.put("AWhileStm", LOOP_STM);
		
		DEFAULT_GROUPING_STM.put("AExitStm", ERRORHANDING_STM);
		DEFAULT_GROUPING_STM.put("AErrorStm", ERRORHANDING_STM);		
		DEFAULT_GROUPING_STM.put("ATrapStm", ERRORHANDING_STM);
		DEFAULT_GROUPING_STM.put("ATixeStm", ERRORHANDING_STM);

		DEFAULT_GROUPING_STM.put("ALetStm", LET_STM);

		DEFAULT_GROUPING_STM.put("ANotYetSpecifiedStm", NOTSPEC_STM);
		DEFAULT_GROUPING_STM.put("ASubclassResponsibilityStm", NOTSPEC_STM);
		DEFAULT_GROUPING_STM.put("ASkipStm", NOTSPEC_STM);

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
