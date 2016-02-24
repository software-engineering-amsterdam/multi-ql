package ast.visitor;

import java.util.HashMap;

import ast.expression.Add;
import ast.expression.AndExpression;
import ast.expression.BinaryExpression;
import ast.expression.Div;
import ast.expression.Eq;
import ast.expression.Expression;
import ast.expression.GEq;
import ast.expression.GT;
import ast.expression.LEq;
import ast.expression.LT;
import ast.expression.Mul;
import ast.expression.NEq;
import ast.expression.Neg;
import ast.expression.Not;
import ast.expression.OrExpression;
import ast.expression.Pos;
import ast.expression.Sub;
import ast.expression.VariableExpression;
import ast.literal.BoolLiteral;
import ast.literal.IntLiteral;
import ast.literal.StringLiteral;
import ast.statement.AssignmentQuestion;
import ast.statement.IfStatement;
import ast.statement.Question;

//vaker doorheen lopen het is niet sequeltieel dus je kan dingen gebruiken die pas later worden gedefinieerd~!
public class TypeChecker extends BasicVisitor {
	private HashMap<String, Types> variableMap;

	public TypeChecker() {
		variableMap = new HashMap<String, Types>();
	}

	@Override
	public Types visit(AssignmentQuestion assignmentQuestion) {
		declareVariable(assignmentQuestion.getVariable().getName(), 
				assignmentQuestion.getVariable().getType().getType(),
				assignmentQuestion.getLineNumber());
		Types t = assignmentQuestion.getVariable().getType().getType();
		compareTypes(assignmentQuestion.getExpression(), t);
		return t;
	}

	@Override
	public Types visit(IfStatement ifStatement) {
		compareTypes(ifStatement.getExpression(), Types.BOOLEAN);
		ifStatement.getBody().accept(this);
		return null;
	}

	@Override
	public Types visit(Question question) {
		declareVariable(question.getVariable().getName(),
				question.getVariable().getType().getType(),
				question.getLineNumber());
		return question.getVariable().getType().getType();
	}

	@Override
	public Types visit(OrExpression orExpression) {
		compareTypesBinaryExpression(orExpression, Types.BOOLEAN);
		return Types.BOOLEAN;
	}

	@Override
	public Types visit(AndExpression andExpression) {
		compareTypesBinaryExpression(andExpression, Types.BOOLEAN);
		return Types.BOOLEAN;
	}

	@Override
	public Types visit(LT lt) {
		compareTypesBinaryExpression(lt, Types.INT);
		return Types.BOOLEAN;
	}

	@Override
	public Types visit(LEq leq) {
		compareTypesBinaryExpression(leq, Types.INT);
		return Types.BOOLEAN;
	}

	@Override
	public Types visit(GT gt) {
		compareTypesBinaryExpression(gt, Types.INT);
		return Types.BOOLEAN;
	}

	@Override
	public Types visit(GEq GEq) {
		compareTypesBinaryExpression(GEq, Types.INT);
		return Types.BOOLEAN;
	}

	@Override
	public Types visit(Eq eq) {
		compareTypesBinaryExpression(eq, Types.INT);
		return Types.BOOLEAN;
	}

	@Override
	public Types visit(NEq neq) {
		compareTypesBinaryExpression(neq, Types.INT);
		return Types.BOOLEAN;
	}

	@Override
	public Types visit(Add add) {
		compareTypesBinaryExpression(add, Types.INT);
		return Types.INT;
	}

	@Override
	public Types visit(Sub sub) {
		compareTypesBinaryExpression(sub, Types.INT);
		return Types.INT;
	}

	@Override
	public Types visit(Mul mul) {
		compareTypesBinaryExpression(mul, Types.INT);
		return Types.INT;
	}

	@Override
	public Types visit(Div div) {
		compareTypesBinaryExpression(div, Types.INT);
		return Types.INT;
	}

	@Override
	public Types visit(Pos pos) {
		compareTypes(pos.getExpression(), Types.INT);
		return Types.INT;
	}

	@Override
	public Types visit(Neg neg) {
		compareTypes(neg.getExpression(), Types.INT);
		return Types.INT;
	}

	@Override
	public Types visit(Not not) {
		compareTypes(not.getExpression(), Types.BOOLEAN);
		return Types.BOOLEAN;
	}

	@Override
	public Types visit(IntLiteral intLiteral) {
		return Types.INT;
	}

	@Override
	public Types visit(BoolLiteral boolLiteral) {
		return Types.BOOLEAN;
	}

	@Override
	public Types visit(StringLiteral stringLiteral) {
		return Types.STRING;
	}

	@Override
	public Types visit(VariableExpression variableExpression) {
		String variableName = variableExpression.getName();
		if (variableMap.containsKey(variableName)) {
			return variableMap.get(variableName);
		}
		System.out.println(String.format("Unknown variable: %s. At line: %s",
							variableName, variableExpression.getLineNumber()));
		return null;
	}

	private void compareTypesBinaryExpression(BinaryExpression e, Types expectedType) {
		compareTypes(e.getLhs(), expectedType);
		compareTypes(e.getRhs(), expectedType);
	}

	private void compareTypes(Expression e, Types expectedType) {
		Types actualType = (Types) e.accept(this);
		if (actualType != expectedType) {
			System.out.println(String.format("Incorrect type. Expected type: %s. Actual type: %s. On lineNumber: %d",
									expectedType, actualType, e.getLineNumber()));
		}
	}

	private void declareVariable(String name, Types type, int lineNumber) {
		if (variableMap.containsKey(name)) {
			if (variableMap.get(name) != type) {
				System.out.println(String.format("Duplicate variable with a different type. Name: %s. Original Type: %s. New Declared Type: %s. LineNumber: %s",
									name, variableMap.get(name), type, lineNumber));
			}
		} else {
			variableMap.put(name, type);
		}
	}
}
