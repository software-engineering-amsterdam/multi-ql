package ast.visitor;

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
import ast.form.Body;
import ast.form.Form;
import ast.literal.BoolLiteral;
import ast.literal.IntLiteral;
import ast.literal.Literal;
import ast.literal.StringLiteral;
import ast.statement.AssignmentQuestion;
import ast.statement.IfStatement;
import ast.statement.Question;
import ast.statement.Statement;

public class TypeChecker extends BasicVisitor{
	private TypeChecker visitor;
	
	public TypeChecker(){
		this.visitor = this;
	}
	
	@Override
	public Types visit(Form form){
		form.getBody().accept(visitor);
		return null;
	}
	
	@Override
	public Types visit(Body body){
		for(Visitable v : body.getStatements()){
			v.accept(visitor);
		}
		return null;
	}
	
	@Override
	public Types visit(Statement statement){
		if(statement.getAssignmentQuestion() != null){
			statement.getAssignmentQuestion().accept(visitor);
		}
		if(statement.getIfStatement() != null){
			statement.getIfStatement().accept(visitor);
		}
		if(statement.getQuestion() != null){
			statement.getQuestion().accept(visitor);
		}
		return null;
	}
	
	@Override
	public Types visit(AssignmentQuestion assignmentQuestion){
		Types t = assignmentQuestion.getVariable().getType().getType();
		compareTypes(assignmentQuestion.getExpression(), t);
		return t;
	}
	
	@Override
	public Types visit(IfStatement ifStatement){
		compareTypes(ifStatement.getExpression(), Types.BOOLEAN);
		ifStatement.getBody().accept(visitor);
		return null;
	}
	
	@Override
	public Types visit(Question question){
		return question.getVariable().getType().getType();
	}
	
	@Override
	public Types visit(OrExpression orExpression){
		compareTypesBinaryExpression(orExpression, Types.BOOLEAN);
		return Types.BOOLEAN;
	}
	
	@Override
	public Types visit(AndExpression andExpression){
		compareTypesBinaryExpression(andExpression, Types.BOOLEAN);
		return Types.BOOLEAN;
	}
	
	@Override
	public Types visit(LT lt){
		compareTypesBinaryExpression(lt, Types.INT);
		return Types.BOOLEAN;
	}
	
	@Override
	public Types visit(LEq leq){
		compareTypesBinaryExpression(leq, Types.INT);
		return Types.BOOLEAN;
	}
	
	@Override
	public Types visit(GT gt){
		compareTypesBinaryExpression(gt, Types.INT);
		return Types.BOOLEAN;
	}
	
	@Override
	public Types visit(GEq GEq){
		compareTypesBinaryExpression(GEq, Types.INT);
		return Types.BOOLEAN;
	}
	
	@Override
	public Types visit(Eq eq){
		compareTypesBinaryExpression(eq, Types.INT);
		return Types.BOOLEAN;
	}
	
	@Override
	public Types visit(NEq neq){
		compareTypesBinaryExpression(neq, Types.INT);
		return Types.BOOLEAN;
	}
	
	@Override
	public Types visit(Add add){
		compareTypesBinaryExpression(add, Types.INT);
		return Types.INT;
	}
	
	@Override
	public Types visit(Sub sub){
		compareTypesBinaryExpression(sub, Types.INT);
		return Types.INT;
	}
	
	@Override
	public Types visit(Mul mul){
		compareTypesBinaryExpression(mul, Types.INT);
		return Types.INT;
	}	
	
	@Override
	public Types visit(Div div){
		compareTypesBinaryExpression(div, Types.INT);
		return Types.INT;
	}
	
	@Override
	public Types visit(Pos pos){
		compareTypes(pos.getExpression(), Types.BOOLEAN);
		return Types.BOOLEAN;
	}
	
	@Override
	public Types visit(Neg neg){
		compareTypes(neg.getExpression(), Types.BOOLEAN);
		return Types.BOOLEAN;
	}
	
	@Override
	public Types visit(Not not){	
		compareTypes(not.getExpression(), Types.BOOLEAN);
		return Types.BOOLEAN;
	}
	
	@Override
	public Types visit(Literal literal){
		if(literal.getBoolLiteral() != null){
			return (Types)literal.getBoolLiteral().accept(visitor);
		}
		if(literal.getIntLiteral() != null){
			return (Types)literal.getIntLiteral().accept(visitor);
		}
		if(literal.getStringLiteral() != null){
			return (Types)literal.getStringLiteral().accept(visitor);
		}
		if(literal.getVariableExpression() != null){
			return (Types)literal.getVariableExpression().accept(visitor);
		}
		return null;
	}
	
	@Override
	public Types visit(IntLiteral intLiteral){
		return Types.INT;
	}
	
	@Override
	public Types visit(BoolLiteral boolLiteral){
		return Types.BOOLEAN;
	}
	
	@Override
	public Types visit(StringLiteral stringLiteral){
		return Types.STRING;
	}
	
	@Override
	public Types visit(VariableExpression variableExpression){
		//TODO: get the type from the context scop or whatever!
		return null;
	}
	
	private void compareTypesBinaryExpression(BinaryExpression e, Types expectedType){
		compareTypes(e.getLhs(), expectedType);
		compareTypes(e.getRhs(), expectedType);	
	}
	
	private void compareTypes(Expression e, Types expectedType){
		Types actualType = (Types)e.accept(visitor);
		if(actualType != expectedType){
			System.out.println(String.format("Incorrect. Expected type: %s. Actual type: %s. On lineNumber: %d", expectedType, actualType, e.getLineNumber()));
		}
	}
}
