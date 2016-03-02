package testEvaluation;


import org.junit.After;
import org.junit.Before;

import semanticAction.evaluation.EvalVisit;
import semanticAction.evaluation.ValueHolder;
import semanticAction.evaluation.Value;
import semanticAction.tree.expressionNode.AbsExpression;
import semanticAction.tree.expressionNode.literal.Integerliteral;

public class TestAbstract {

	//protected final Integerliteral int4 = new Integerliteral(4);
	
	private ValueHolder table = new ValueHolder();
	private EvalVisit eval;

	@Before 
	public void setUp() { 
		eval = new EvalVisit(table); 
	}
	
	public Value evaluate(AbsExpression expression) {
		return expression.accept(this.eval);
	}
	
	@After
	public void tearDown() { eval = null; }

	}


