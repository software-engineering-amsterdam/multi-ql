package org.uva.sea.ql.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uva.sea.ql.ast.block.Block;
import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.expression.Comparison.Equal;
import org.uva.sea.ql.ast.expression.Comparison.Greater;
import org.uva.sea.ql.ast.expression.Comparison.GreaterOrEqual;
import org.uva.sea.ql.ast.expression.Comparison.Less;
import org.uva.sea.ql.ast.expression.Comparison.LessOrEqual;
import org.uva.sea.ql.ast.expression.Comparison.NotEqual;
import org.uva.sea.ql.ast.expression.Literal.BooleanLiteral;
import org.uva.sea.ql.ast.expression.Literal.Identifier;
import org.uva.sea.ql.ast.expression.Literal.IntegerLiteral;
import org.uva.sea.ql.ast.expression.Literal.StringLiteral;
import org.uva.sea.ql.ast.expression.Logical.And;
import org.uva.sea.ql.ast.expression.Logical.Binary;
import org.uva.sea.ql.ast.expression.Logical.Or;
import org.uva.sea.ql.ast.expression.Numerical.Add;
import org.uva.sea.ql.ast.expression.Numerical.Div;
import org.uva.sea.ql.ast.expression.Numerical.Mul;
import org.uva.sea.ql.ast.expression.Numerical.Sub;
import org.uva.sea.ql.ast.expression.Parenthesis.Parenthesis;
import org.uva.sea.ql.ast.expression.Unary.Negative;
import org.uva.sea.ql.ast.expression.Unary.Not;
import org.uva.sea.ql.ast.expression.Unary.Positive;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.form.FormVisitor;
import org.uva.sea.ql.ast.statement.ComputedQuestion;
import org.uva.sea.ql.ast.statement.IfElseStatement;
import org.uva.sea.ql.ast.statement.IfStatement;
import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.ast.statement.Statement;
import org.uva.sea.ql.ast.statement.StatementVisitor;


/// na mapparw IfStatement me lista apo questionItems?

public class CollectIdentifiersInConditions implements FormVisitor,StatementVisitor,ExpressionVisitor<Set<Identifier>> {
	
	private Map<Identifier, List<IfStatement>> identifiersInConditions;

	
	public CollectIdentifiersInConditions(Form form) {
		this.identifiersInConditions = new HashMap<Identifier, List<IfStatement>>();
		visitForm(form);
	}
	
	private Set<Identifier> getIdentifiersFromBinary(Binary node) {
		Set<Identifier> identifiers = new HashSet<>();
		Set<Identifier> identifiersLeftExpression = node.getLeftExpression().accept(this);
		Set<Identifier> identifiersRightExpression = node.getRightExpression().accept(this);
		identifiers = addIdentifiers(identifiers,identifiersLeftExpression);
		identifiers = addIdentifiers(identifiers,identifiersRightExpression);
		return identifiers;
	}
	
	private Set<Identifier> addIdentifiers(
			Set<Identifier> identifiers,
			Set<Identifier> identifiersExpression) {
		
		if (identifiersExpression.size() != 0) {
			for(Identifier id: identifiersExpression)
				identifiers.add(id);
		}
		return identifiers;
	}
	
	@Override
	public Set<Identifier> visit(Equal node) {
		return getIdentifiersFromBinary(node);
	}

	@Override
	public Set<Identifier> visit(NotEqual node) {
		return getIdentifiersFromBinary(node);
	}

	@Override
	public Set<Identifier> visit(Greater node) {
		return getIdentifiersFromBinary(node);
	}

	@Override
	public Set<Identifier> visit(GreaterOrEqual node) {
		return getIdentifiersFromBinary(node);
	}

	@Override
	public Set<Identifier> visit(Less node) {
		return getIdentifiersFromBinary(node);
	}

	@Override
	public Set<Identifier> visit(LessOrEqual node) {
		return getIdentifiersFromBinary(node);
	}

	@Override
	public Set<Identifier> visit(BooleanLiteral node) {
		return new HashSet<>();
	}

	@Override
	public Set<Identifier> visit(Identifier node) {	
		Set<Identifier> identifiers = new HashSet<>();
		identifiers.add(node);
		return identifiers;
	}

	@Override
	public Set<Identifier> visit(IntegerLiteral node) {
		return new HashSet<>();
	}

	@Override
	public Set<Identifier> visit(StringLiteral node) {
		return new HashSet<>();
	}

	@Override
	public Set<Identifier> visit(And node) {
		return getIdentifiersFromBinary(node);
	}

	@Override
	public Set<Identifier> visit(Or node) {
		return getIdentifiersFromBinary(node);
	}

	@Override
	public Set<Identifier> visit(Add node) {		
		return getIdentifiersFromBinary(node);
	}

	

	@Override
	public Set<Identifier> visit(Sub node) {
		return getIdentifiersFromBinary(node);
	}

	@Override
	public Set<Identifier> visit(Mul node) {
		return getIdentifiersFromBinary(node);
	}

	@Override
	public Set<Identifier> visit(Div node) {
		return getIdentifiersFromBinary(node);
	}

	@Override
	public Set<Identifier> visit(Parenthesis node) {
		return null;
	}

	@Override
	public Set<Identifier> visit(Not node) {
		Set<Identifier> identifiers = new HashSet<>();
		
		Set<Identifier> identifiersExpression = node.getExpression().accept(this);
		identifiers = addIdentifiers(identifiers,identifiersExpression);
		
		return identifiers;
	}

	@Override
	public Set<Identifier> visit(Positive node) {
		Set<Identifier> identifiers = new HashSet<>();
		
		Set<Identifier> identifiersExpression = node.getExpression().accept(this);
		identifiers = addIdentifiers(identifiers,identifiersExpression);
		
		return identifiers;
	}

	@Override
	public Set<Identifier> visit(Negative node) {
		Set<Identifier> identifiers = new HashSet<>();
		
		Set<Identifier> identifiersExpression = node.getExpression().accept(this);
		identifiers = addIdentifiers(identifiers,identifiersExpression);
		
		return identifiers;
	}

	@Override
	public void visitComputedQuestion(ComputedQuestion computedQuestion) {
		
	}

	@Override
	public void visitQuestion(Question question) {
		
	}

	@Override
	public void visitIfStatement(IfStatement ifStatement) {
		//Set<Identifier> identifiers = new HashSet<>();
		
		Set<Identifier> collectedIdentifiers = ifStatement.getExpression().accept(this);
		
		if (collectedIdentifiers.size()!=0) {
			
			System.out.println("*** Identifiers of boolean condition below: ****");
			for (Identifier id: collectedIdentifiers)
				System.out.println("Identifier: " + id.getValue());
		}
		
		if (collectedIdentifiers.size()!=0) {
			for (Identifier id: collectedIdentifiers)
				if(!identifiersInConditions.keySet().contains(id)) {
					List<IfStatement> statements = new ArrayList<>();
					statements.add(ifStatement);
					identifiersInConditions.put(id, statements);
				}
			
				else {
					identifiersInConditions.get(id).add(ifStatement);		// fix this....
				}
		}
	}

	@Override
	public void visitIfElseStatement(IfElseStatement ifElseStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitForm(Form form) {
		form.getBlock().accept(this);		
		
	}

	@Override
	public void visitBlock(Block block) {
		for (Statement statement: block.getStatements()) {
			statement.accept(this);
		}
		
	}

}
