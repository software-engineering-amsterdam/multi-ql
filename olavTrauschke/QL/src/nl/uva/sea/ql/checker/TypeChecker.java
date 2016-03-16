package nl.uva.sea.ql.checker;

import java.util.*;
import nl.uva.sea.ql.ast.ConditionalStatement;
import nl.uva.sea.ql.ast.expr.*;
import nl.uva.sea.ql.ast.question.*;

/**
 * Visitor to check the types of objects in an AST.
 * 
 * @author Olav Trauschke
 * @version 7-mrt-2016
 */
public class TypeChecker implements ASTVisitor {
    
    /**
     * Description of the type boolean.
     */
    public static final String BOOLEAN = "boolean";
    
    /**
     * Description of the type date.
     */
    public static final String DATE = "date";
    
    /**
     * Description of the type decimal.
     */
    public static final String DECIMAL = "decimal";
    
    /**
     * Description of the type int.
     */
    public static final String INT = "int";
    
    /**
     * Description of the type money.
     */
    public static final String MONEY = "money";
    
    /**
     * Error presented to the user when an <code>Add</code> with no two numeric
     * nor two string <code>Expr</code>s was found.
     */
    public static final String ADDITION_TYPE_ERROR
            = "Addition with no two numeric, nor two string operands found";
    
    /**
     * Error presented to the user when a <code>BooleanConjunctiveExpr</code>
     * containing a non-boolean <code>Expr</code> was found.
     */
    public static final String BOOLEAN_CONJUNCTION_OF_NON_BOOLEAN_ERROR
            = "Boolean conjunction of non-boolean values found";
    
    /**
     * Error presented to the user when a <code>ComparisonExpr</code> containing
     * <code>Expr</code>s of different types was found.
     */
    public static final String COMPARISON_OF_DIFFERENT_TYPES_ERROR
            = "Comparison of expressions of different types was found";
    
    /**
     * Error presented to the user when a <code>Not</code> has a non-boolean
     * content.
     */
    public static final String NEGATION_OF_NON_BOOLEAN_ERROR
            = "Negation of non-boolean value found";
    
    public static final String NEGATIVE_OF_NON_NUMERIC_ERROR
            = "Negative of a non-numeric value found";
    
    /**
     * Error presented to the user when a <code>ConditionalStatement</code> has
     * a non-boolean condition.
     */
    public static final String NON_BOOLEAN_CONDITION_ERROR
            = "Non-boolean condition for conditional statement found";
    
    /**
     * Error presented to the user when a <code>BinaryNumericOperatorExpr</code>
     * containing a non-numeric <code>Expr</code> was found.
     */
    public static final String NUMERIC_OPERATOR_WITH_NON_NUMERIC_OPERAND_ERROR
            = "Numeric operator with non-numeric operand found";
    
    /**
     * Error presented to the user when a <code>OrderedComapisonExpr</code>
     * containing a non-numeric <code>Expr</code> was found.
     */
    public static final String ORDERED_COMPARISON_OF_NON_NUMERIC_ERROR
            = "Comparison that depends on order of non-numeric values found";
    
    private final List<String> errors;
    private final Map<Ident,Question> questionTypes;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theQuestionTypes a <code>Map</code> from each <code>Ident</code>
     *                          <code>this TypeChecker</code> might
     *                          need the <code>type</code> of (that is: each
     *                          identifier it will visit in an <code>Expr</code>)
     *                          to a <code>Question</code> with that <code>Ident</code>
     */
    public TypeChecker(Map<Ident,Question> theQuestionTypes) {
        errors = new ArrayList();
        questionTypes = theQuestionTypes;
    }
    
    /**
     * @return a <code>List</code> of the errors generated during the
     *          <code>visit</code>s <code>this TypeChecker</code> has
     *          performed
     */
    public List<String> getErrors() {
        return errors;
    }
    
    /**
     * Add an error if a <code>ConditionalStatement</code> with a <code>condition</code>
     * that is not a boolean was found.
     * 
     * @param s the <code>ConditionalStatement</code> to check
     */
    @Override
    public void visit(ConditionalStatement s) {
        Expr condition = s.getCondition();
        if (!isBoolean(condition)) {
            errors.add(NON_BOOLEAN_CONDITION_ERROR);
        }
    }
    
    /**
     * Add an error if a <code>BooleanQuestion</code> with a non-boolean
     * <code>calculation</code> was found.
     * 
     * @param q the <code>BooleanQuestion</code> to check
     */
    @Override
    public void visit(BooleanQuestion q) {
        Expr calculation = q.getCalculation();
        if (calculation != null && !calculation.isBoolean(questionTypes)) {
            addQuestionTypeError(BOOLEAN);
        }
    }
    
    /**
     * Add an error if a <code>DateQuestion</code> with a
     * <code>calculation</code> was found.
     * 
     * @param q the <code>DateQuestion</code> to check
     */
    @Override
    public void visit(DateQuestion q) {
        Expr calculation = q.getCalculation();
        if (calculation != null) {
            addQuestionTypeError(DATE);
        }
    }
    
    /**
     * Add an error if a <code>DecimalQuestion</code> with a non-decimal
     * (or integer) <code>calculation</code> was found.
     * 
     * @param q the <code>DecimalQuestion</code> to check
     */
    @Override
    public void visit(DecimalQuestion q) {
        Expr calculation = q.getCalculation();
        if (calculation != null && !isDecimal(calculation) && !isInt(calculation)) {
            addQuestionTypeError(DECIMAL);
        }
    }
    
    /**
     * Add an error if an <code>IntQuestion</code> with a non-integer
     * <code>calculation</code> was found.
     * 
     * @param q the <code>IntQuestion</code> to check
     */
    @Override
    public void visit(IntQuestion q) {
        Expr calculation = q.getCalculation();
        if (calculation != null && !isInt(calculation)) {
            addQuestionTypeError(INT);
        }
    }
    
    /**
     * Add an error if a <code>MoneyQuestion</code> with a non-money
     * <code>calculation</code> was found.
     * 
     * @param q the <code>MoneyQuestion</code> to check
     */
    @Override
    public void visit(MoneyQuestion q) {
        Expr calculation = q.getCalculation();
        if (calculation != null && !isMoney(calculation)) {
            addQuestionTypeError(MONEY);
        }
    }
    
    /**
     * Add an error if a <code>StringQuestion</code> with a non-string
     * <code>calculation</code> was found.
     * 
     * @param q the <code>StringQuestion</code> to check
     */
    @Override
    public void visit(StringQuestion q) {
        Expr calculation = q.getCalculation();
        if (calculation != null && !isString(calculation)) {
            addQuestionTypeError(MONEY);
        }
    }
    
    /**
     * Add an error if an <code>Add</code> that has no two numeric operands nor
     * two string operands was found and set the type of the <code>Add</code>.
     * Sets the <code>Add</code> to be decimal, integer, money and string when
     * an error was added, to avoid unnecessary errors.
     * 
     * @param a the <code>Add</code> to check and set the type of
     */
    @Override
    public void visit(Add a) {
        Expr firstExpr = a.getFirstExpr();
        Expr secondExpr = a.getSecondExpr();
        boolean bothNumeric = isNumeric(firstExpr) && isNumeric(secondExpr);
        boolean bothString = isString(firstExpr) && isString(secondExpr);
        if (!bothNumeric && !bothString) {
            errors.add(ADDITION_TYPE_ERROR);
        }
        else if (bothString) {
            a.setIsString(true);
        }
        else if (isMoney(firstExpr) || isMoney(secondExpr)) {
            a.setIsMoney(true);
        }
        else if (isDecimal(firstExpr) || isDecimal(secondExpr)) {
            a.setIsDecimal(true);
        }
        else {
            a.setIsInt(true);
        }
    }
    
    /**
     * Add an error if a <code>BinaryNumericOperatorExpr</code> containing a
     * non-numeric value was found and set the type of the
     * <code>BinaryNumericOperatorExpr</code>. Sets the
     * <code>BinaryNumericOperatorExpr</code> to be decimal, integer and money
     * when an error was added, to avoid unnecessary errors.
     * 
     * @param e the <code>BinaryNumericOperatorExpr</code> to check and set the
     *          type of
     */
    @Override
    public void visit(BinaryNumericOperatorExpr e) {
        Expr firstExpr = e.getFirstExpr();
        Expr secondExpr = e.getSecondExpr();
        if (!isNumeric(firstExpr) || !isNumeric(secondExpr)) {
            errors.add(NUMERIC_OPERATOR_WITH_NON_NUMERIC_OPERAND_ERROR);
            e.setIsDecimal(true);
            e.setIsInt(true);
            e.setIsMoney(true);
        }
        else if (isMoney(firstExpr) || isMoney(secondExpr)) {
            e.setIsMoney(true);
        }
        else if (isInt(firstExpr) && isInt(secondExpr)) {
            e.setIsInt(true);
        }
        else {
            e.setIsDecimal(true);
        }
    }
    
    /**
     * Add an error if a <code>BooleanConjunctiveExpr</code> containing a
     * non-boolean value was found.
     * 
     * @param e the <code>BooleanConjunctiveExpr</code> to check
     */
    @Override
    public void visit(BooleanConjunctiveExpr e) {
        Expr firstExpr = e.getFirstExpr();
        Expr secondExpr = e.getSecondExpr();
        if (!isBoolean(firstExpr) || !isBoolean(secondExpr)) {
            errors.add(BOOLEAN_CONJUNCTION_OF_NON_BOOLEAN_ERROR);
        }
    }
    
    /**
     * Add an error if a <code>ComparisonExpr</code> containing <code>Expr</code>s
     * of different types was found.
     * 
     * @param e the <code>ComparisonExpr</code> to check
     */
    @Override
    public void visit(ComparisonExpr e) {
        Expr firstExpr = e.getFirstExpr();
        Expr secondExpr = e.getSecondExpr();
        boolean bothBoolean = isBoolean(firstExpr) && isBoolean(secondExpr);
        boolean bothNumeric = isNumeric(firstExpr) && isNumeric(secondExpr);
        boolean bothString = isString(firstExpr) && isString(secondExpr);
        if (!bothBoolean && !bothNumeric && !bothString) {
            errors.add(COMPARISON_OF_DIFFERENT_TYPES_ERROR);
        }
    }
    
    /**
     * Add an error if a <code>Neg</code> with a non-numeric content was found.
     * 
     * @param n the <code>Neg</code> to check
     */
    @Override
    public void visit(Neg n) {
        Expr content = n.getContent();
        if (!isNumeric(content)) {
            errors.add(NEGATIVE_OF_NON_NUMERIC_ERROR);
        }
    }
    
    /**
     * Add an error if a <code>Not</code> with a non-boolean content was found.
     * 
     * @param n the <code>Not</code> to check
     */
    @Override
    public void visit(Not n) {
        Expr content = n.getContent();
        if (!isBoolean(content)) {
            errors.add(NEGATION_OF_NON_BOOLEAN_ERROR);
        }
    }
    
    /**
     * Add an error if a <code>OrderedComparisonExpr</code> containing a
     * non-numeric <code>Expr</code> was found.
     * 
     * @param e the <code>OrderedComparisonExpr</code> to check
     */
    @Override
    public void visit(OrderedComparisonExpr e) {
        Expr firstExpr = e.getFirstExpr();
        Expr secondExpr = e.getSecondExpr();
        if (!isNumeric(firstExpr) || !isNumeric(secondExpr)) {
            errors.add(ORDERED_COMPARISON_OF_NON_NUMERIC_ERROR);
        }
    }
    
    /**
     * Checks whether an <code>Expr</code> has a boolean value.
     * 
     * @param e the <code>Expr</code> to check
     * @return <code>true</code> if and only if
     *          <code>e.isBoolean(questionTypes)</code> returns <code>true</code>
     */
    private boolean isBoolean(Expr e) {
        return e.isBoolean(questionTypes);
    }
    
    /**
     * Checks whether an <code>Expr</code> has a decimal value.
     * 
     * @param e the <code>Expr</code> to check
     * @return <code>true</code> if and only if <code>e.isDecimal(questionTypes</code>
     *          returns <code>true</code>
     */
    private boolean isDecimal(Expr e) {
        return e.isDecimal(questionTypes);
    }
    
    /**
     * Checks whether an <code>Expr</code> has an integer value.
     * 
     * @param e the <code>Expr</code> to check
     * @return <code>true</code> if and only if <code>e.isInt(questionTypes)</code>
     *          returns <code>true</code>
     */
    private boolean isInt(Expr e) {
        return e.isInt(questionTypes);
    }
    
    /**
     * Checks whether an <code>Expr</code> has a money value.
     * 
     * @param e the <code>Expr</code> to check
     * @return <code>true</code> if and only if <code>e.isMoney(questionTypes)</code>
     *          returns <code>true</code>
     */
    private boolean isMoney(Expr e) {
        return e.isMoney(questionTypes);
    }
    
    /**
     * Checks whether an <code>Expr</code> has a string value.
     * 
     * @param e the <code>Expr</code> to check
     * @return <code>true</code> if and only if <code>e.isString(questionTypes)</code>
     *          returns <code>true</code>
     */
    private boolean isString(Expr e) {
        return e.isString(questionTypes);
    }
    
    /**
     * Checks whether an <code>Expr</code> has a numeric value.
     * 
     * @param e the <code>Expr</code> to check
     * @return <code>true</code> if and only if <code>e.isNumeric(questionTypes</code>
     *          returns <code>true</code>
     */
    private boolean isNumeric(Expr e) {
        return e.isNumeric(questionTypes);
    }
    
    /**
     * Add an error stating that a <code>Question</code> with a given type was
     * found to be calculated by an <code>Expr</code> of another type.
     * 
     * @param type a <code>String</code> describing the type of the <code>Question</code>
     */
    private void addQuestionTypeError(String type) {
        errors.add("Question of type " + type + " found with calculation of another type.");
    }
}
