package nl.uva.sc.ql.gui.state;

import java.util.List;

import nl.uva.sc.ql.gui.VisitorExpression;
import nl.uva.sc.ql.parser.ast.AdditionNode;
import nl.uva.sc.ql.parser.ast.AndNode;
import nl.uva.sc.ql.parser.ast.BooleanNode;
import nl.uva.sc.ql.parser.ast.DifferentNode;
import nl.uva.sc.ql.parser.ast.DivisionNode;
import nl.uva.sc.ql.parser.ast.EqualsNode;
import nl.uva.sc.ql.parser.ast.ExpressionNode;
import nl.uva.sc.ql.parser.ast.GreatEqualsThanNode;
import nl.uva.sc.ql.parser.ast.GreatThanNode;
import nl.uva.sc.ql.parser.ast.IdentifierNode;
import nl.uva.sc.ql.parser.ast.IntegerNode;
import nl.uva.sc.ql.parser.ast.LessEqualsThanNode;
import nl.uva.sc.ql.parser.ast.LessThanNode;
import nl.uva.sc.ql.parser.ast.MultiplicationNode;
import nl.uva.sc.ql.parser.ast.NotNode;
import nl.uva.sc.ql.parser.ast.OrNode;
import nl.uva.sc.ql.parser.ast.StringNode;
import nl.uva.sc.ql.parser.ast.SubtractionNode;

public class CreateListOfIdentifiersInExpression implements VisitorExpression {
	
	private List<String> listIdentifiers;
	
	public CreateListOfIdentifiersInExpression(List<String> listIdentifiers) {
		this.listIdentifiers = listIdentifiers;
	}
	
	@Override
	public void visit(AdditionNode additionNode) {
		ExpressionNode left = additionNode.getLeft();
		ExpressionNode right = additionNode.getRight();	
		
		left.accept(this);
		right.accept(this);		
	}

	@Override
	public void visit(AndNode andNode) {
		ExpressionNode left = andNode.getLeft();
		ExpressionNode right = andNode.getRight();
		
		left.accept(this);
		right.accept(this);		
	}

	@Override
	public void visit(BooleanNode booleanNode) {		
	}

	@Override
	public void visit(DifferentNode differentNode) {
		ExpressionNode left = differentNode.getLeft();
		ExpressionNode right = differentNode.getRight();
		
		left.accept(this);
		right.accept(this);		
	}

	@Override
	public void visit(DivisionNode divisionNode) {
		ExpressionNode left = divisionNode.getLeft();
		ExpressionNode right = divisionNode.getRight();
		
		left.accept(this);
		right.accept(this);		
	}

	@Override
	public void visit(EqualsNode equalsNode) {
		ExpressionNode left = equalsNode.getLeft();
		ExpressionNode right = equalsNode.getRight();
		
		left.accept(this);
		right.accept(this);		
	}

	@Override
	public void visit(GreatEqualsThanNode greatEqualsThanNode) {
		ExpressionNode left = greatEqualsThanNode.getLeft();
		ExpressionNode right = greatEqualsThanNode.getRight();
		
		left.accept(this);
		right.accept(this);		
	}

	@Override
	public void visit(GreatThanNode greatThanNode) {
		ExpressionNode left = greatThanNode.getLeft();
		ExpressionNode right = greatThanNode.getRight();
		
		left.accept(this);
		right.accept(this);		
	}

	@Override
	public void visit(IdentifierNode identifierNode) {
		String identifier = identifierNode.getName();

		listIdentifiers.add(identifier);
	}

	@Override
	public void visit(IntegerNode integerNode) {		
	}

	@Override
	public void visit(LessEqualsThanNode lessEqualsThanNode) {
		ExpressionNode left = lessEqualsThanNode.getLeft();
		ExpressionNode right = lessEqualsThanNode.getRight();
		
		left.accept(this);
		right.accept(this);		
	}

	@Override
	public void visit(LessThanNode lessThanNode) {
		ExpressionNode left = lessThanNode.getLeft();
		ExpressionNode right = lessThanNode.getRight();
		
		left.accept(this);
		right.accept(this);	
	}

	@Override
	public void visit(MultiplicationNode multiplicationNode) {
		ExpressionNode left = multiplicationNode.getLeft();
		ExpressionNode right = multiplicationNode.getRight();
		
		left.accept(this);
		right.accept(this);		
	}

	@Override
	public void visit(NotNode notNode) {
		ExpressionNode expression = notNode.getExpression();
		
		expression.accept(this);		
	}

	@Override
	public void visit(OrNode orNode) {
		ExpressionNode left = orNode.getLeft();
		ExpressionNode right = orNode.getRight();
		
		left.accept(this);
		right.accept(this);		
	}

	@Override
	public void visit(StringNode stringNode) {		
	}

	@Override
	public void visit(SubtractionNode subtractionNode) {
		ExpressionNode left = subtractionNode.getLeft();
		ExpressionNode right = subtractionNode.getRight();	
		
		left.accept(this);
		right.accept(this);		
	}
}
