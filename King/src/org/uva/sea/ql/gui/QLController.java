/*package org.uva.sea.ql.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JRadioButton;

import org.joda.money.Money;
import org.uva.sea.ql.ast.domain.Block;
import org.uva.sea.ql.ast.domain.Form;
import org.uva.sea.ql.ast.domain.IFblock;
import org.uva.sea.ql.ast.domain.Question;
import org.uva.sea.ql.ast.domain.ReadOnlyQuestion;
import org.uva.sea.ql.ast.expr.VarExpr;
import org.uva.sea.ql.ast.expr.binary.AND;
import org.uva.sea.ql.ast.expr.binary.Equal;
import org.uva.sea.ql.ast.expr.binary.GreaterOrEqual;
import org.uva.sea.ql.ast.expr.binary.GreaterThan;
import org.uva.sea.ql.ast.expr.binary.NotEqual;
import org.uva.sea.ql.ast.expr.binary.OR;
import org.uva.sea.ql.ast.expr.binary.SmallerOrEqual;
import org.uva.sea.ql.ast.expr.binary.SmallerThan;
import org.uva.sea.ql.ast.expr.literal.BooleanLiteral;
import org.uva.sea.ql.ast.expr.literal.IntegerLiteral;
import org.uva.sea.ql.ast.expr.literal.MoneyLiteral;
import org.uva.sea.ql.ast.expr.literal.StringLiteral;
import org.uva.sea.ql.ast.expr.math.Add;
import org.uva.sea.ql.ast.expr.math.Div;
import org.uva.sea.ql.ast.expr.math.Mul;
import org.uva.sea.ql.ast.expr.math.Sub;
import org.uva.sea.ql.ast.expr.unary.NOT;
import org.uva.sea.ql.ast.expr.unary.Negative;
import org.uva.sea.ql.ast.expr.unary.Positive;
import org.uva.sea.ql.ast.visitors.QLDomainVisitor;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;
import org.uva.sea.ql.evalutor.BooleanValue;
import org.uva.sea.ql.evalutor.IntegerValue;
import org.uva.sea.ql.evalutor.MoneyValue;
import org.uva.sea.ql.evalutor.StringValue;
import org.uva.sea.ql.evalutor.Value;

public class QLController implements QLNodeVisitor<Value>,QLDomainVisitor,ItemListener {
	private QLBlockView qLBlockView;
	private QLView qLView;
	private Map<String, Value> identifierValues;
	IFblock statement;
	QLViewQuestion aQLViewQuestion;
	Form form;
	private Set<String> conditionId = new HashSet<String>();
	private boolean isInCondition = false;
	public QLController(Form form) {
		this.form = form;
		identifierValues = new HashMap<>();
		qLBlockView = new QLBlockView();
		qLView = new QLView();
		form.accept(this);
	}

	@Override
	public void visit(Form form) {
		System.out.println("<----------------->");
		//System.out.println("I'm in form");
		//System.out.println(identifierValues);
		form.getBody().accept(this);
		
	}

	@Override
	public void visit(Block block) {
		//System.out.println("I'm in block");
		//System.out.println(identifierValues);
		for (Question q : block.getQuestions()) {
			q.accept(this);
		}
		
		for (IFblock ib: block.getStatements()) {
			ib.accept(this);
		}
		
	}

	@Override
	public void visit(IFblock statement) {
		isInCondition = true;
		//System.out.println("I'm in statement");
		//System.out.println(identifierValues);
		Value condition = statement.getCondition().accept(this);
		Value trueCondition = new BooleanValue(true);
		this.statement = statement;
		//System.out.println(conditionId.toString());
		//System.out.println(condition+" "+trueCondition);
		if(condition.equal(trueCondition).getBooleanValue()){
			
			
			System.out.println(isInCondition);
			for (Question ibq : statement.getBody().getQuestions()) {
				
				ibq.accept(this);
			}
		
		}
	}

	@Override
	public void visit(Question question) {
		//System.out.println("I'm in question");
		//System.out.println(identifierValues);
		String identifier = question.getVariableId().getIdentifier().getName();
		if(!this.questionAlreadyInView(identifier)){
		    aQLViewQuestion = new QLViewQuestion(question);
			
			
			if(!aQLViewQuestion.isComputedQuestion()){
				aQLViewQuestion.getqIdentifier().addItemListener(this);
				addValue(identifier,new BooleanValue(false));
			}else{
				//sysout
				aQLViewQuestion.getqComputed().setText(Money.parse("USD 0").toString());
				addValue(identifier,new MoneyValue(Money.parse("USD 0")));
			}
			qLBlockView.addQuestionView(aQLViewQuestion);
			qLView.addBlockToFrame(qLBlockView);
		}
		//System.out.println(aQLViewQuestion.getqIdentifier().getName());
	}

	@Override
	public void visit(ReadOnlyQuestion readOnlyQuestion) {
		//System.out.println("I'm in read only question");
		//System.out.println(identifierValues);
		String identifier = readOnlyQuestion.getVariableId().getIdentifier().getName();
		if(!this.questionAlreadyInView(identifier)){
			Value computed = readOnlyQuestion.getExpression().accept(this);
			addValue(identifier,computed);
			QLViewReadOnlyQuestion readOnlyQLViewQuestion = new QLViewReadOnlyQuestion(readOnlyQuestion);
			readOnlyQLViewQuestion.getqComputed().setText(computed.getMoneyValue().toString());
			qLBlockView.addQuestionView(readOnlyQLViewQuestion);
			qLView.addBlockToFrame(qLBlockView);
		}
		//readOnlyQuestion.getExpression().accept(this);
		
	}

	@Override
	public Value visit(Add add) {
		Value left = add.getFirstExpression().accept(this);
		Value right = add.getSecondExpression().accept(this);
		return left.add(right);
	}

	@Override
	public Value visit(AND and) {
		Value left = and.getFirstExpression().accept(this);
		Value right = and.getSecondExpression().accept(this);
		return left.and(right);
	}

	@Override
	public Value visit(Div div) {
		Value left = div.getFirstExpression().accept(this);
		Value right = div.getSecondExpression().accept(this);
		return left.div(right);
	}

	@Override
	public Value visit(Equal eq) {
		Value left = eq.getFirstExpression().accept(this);
		Value right = eq.getSecondExpression().accept(this);
		return left.equal(right);
	}

	@Override
	public Value visit(GreaterOrEqual geq) {
		Value left = geq.getFirstExpression().accept(this);
		Value right = geq.getSecondExpression().accept(this);
		return left.greaterOrEqual(right);
	}

	@Override
	public Value visit(GreaterThan gt) {
		Value left = gt.getFirstExpression().accept(this);
		Value right = gt.getSecondExpression().accept(this);
		return left.greaterThan(right);
	}

	@Override
	public Value visit(SmallerOrEqual leq) {
		Value left = leq.getFirstExpression().accept(this);
		Value right = leq.getSecondExpression().accept(this);
		return left.lessOrEqual(right);
	}

	@Override
	public Value visit(SmallerThan lt) {
		Value left = lt.getFirstExpression().accept(this);
		Value right = lt.getSecondExpression().accept(this);
		return left.lessThan(right);
	}

	@Override
	public Value visit(Mul mul) {
		Value left = mul.getFirstExpression().accept(this);
		Value right = mul.getSecondExpression().accept(this);
		return left.mul(right);
	}

	@Override
	public Value visit(NotEqual neq) {
		Value left = neq.getFirstExpression().accept(this);
		Value right = neq.getSecondExpression().accept(this);
		return left.notEqual(right);
	}

	@Override
	public Value visit(Negative neg) {
		Value e = neg.getExpression().accept(this);
		return e.negative();
	}

	@Override
	public Value visit(NOT not) {
		Value e = not.getExpression().accept(this);
		return e.not();
	}

	@Override
	public Value visit(OR or) {
		Value left = or.getFirstExpression().accept(this);
		Value right = or.getSecondExpression().accept(this);
		return left.or(right);
	}

	@Override
	public Value visit(Positive pos) {
		Value e = pos.getExpression().accept(this);
		return e.positive();
	}

	@Override
	public Value visit(Sub sub) {
		Value left = sub.getFirstExpression().accept(this);
		Value right = sub.getSecondExpression().accept(this);
		return left.sub(right);
	}

	@Override
	public Value visit(IntegerLiteral intLiteral) {
		return new IntegerValue(intLiteral.getValue());
	}

	@Override
	public Value visit(BooleanLiteral boolLiteral) {
		return new BooleanValue(boolLiteral.getValue());
	}

	@Override
	public Value visit(StringLiteral stringLiteral) {
		return new StringValue(stringLiteral.getValue());
	}

	@Override
	public Value visit(MoneyLiteral moneyLiteral) {
		return new MoneyValue(moneyLiteral.getValue());
	}

	@Override
	public Value visit(VarExpr varExpr) {
		
		if(isInCondition){
			conditionId.add(varExpr.getIdentifier().getName());
			System.out.println(conditionId);
		}
		return getValue(varExpr.getIdentifier().getName());
	}
	
	public void addValue(String identifier, Value value) {
		identifierValues.put(identifier, value);
	}
	public boolean questionAlreadyInView(String identifier) {
		return identifierValues.containsKey(identifier);
	}
	
	public Value getValue(String identifier) {
		assert !identifier.isEmpty();
		return identifierValues.get(identifier);
	}
	
	public void showQL() {
		qLView.showQL();
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		int state = event.getStateChange();
		JRadioButton btn = (JRadioButton) event.getSource();
        if (state == ItemEvent.SELECTED) {
        	addValue(btn.getName(),new BooleanValue(true));
        	//this.qLView.resetQL();
        	btn.setSelected(true);
        	//statement.accept(this);
        	form.accept(this);
        	this.showQL();
 
        } else if (state == ItemEvent.DESELECTED) {
        	
        	
        	btn.setSelected(false);
        	if(conditionId.contains(btn.getName())){
	    		this.qLBlockView.getqlBlock().removeAll();
	    		this.identifierValues.clear();
	    		
	    		form.accept(this);
	        	this.showQL();
        	}
        	//statement.accept(this);
        }
        
	}


}
*/