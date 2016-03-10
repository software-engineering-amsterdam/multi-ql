package org.uva.sea.ql.eval;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.uva.sea.ql.ast.expr.logic.And;
import org.uva.sea.ql.ast.expr.logic.GEq;
import org.uva.sea.ql.ast.expr.logic.LT;
import org.uva.sea.ql.ast.expr.logic.Or;
import org.uva.sea.ql.ast.expr.math.Add;
import org.uva.sea.ql.ast.expr.math.Div;
import org.uva.sea.ql.ast.expr.terminals.BooleanLiteral;
import org.uva.sea.ql.ast.expr.terminals.IntegerLiteral;
import org.uva.sea.ql.ast.expr.terminals.Variable;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.value.IntValue;
import org.uva.sea.ql.value.MoneyValue;
import org.uva.sea.ql.value.Value;

public class TestEvalMath {

	ValueMap valueMap = new ValueMap();
	
	@Before 
	public void initialize() {
		valueMap.putValueInMap("var1", new MoneyValue(4.5));
		valueMap.putValueInMap("var2", new IntValue(4));
  	}
	
	@Test
	public void testAddIntInt() {
		Value value1 = new IntValue(4);
		Value value2 = new IntValue(5);
		Value result = value1.add(value2);
		assertEquals(9, result.getValue());
	}
	
	@Test
	public void testAddMoneyMoney() {
		Value value1 = new MoneyValue(4.5);
		Value value2 = new MoneyValue(4.0);
		Value result = value1.add(value2);
		assertEquals(8.5, result.getValue());
	}
	
	@Test
	public void testAddMoneyInt() {
		Value value1 = new MoneyValue(4.5);
		Value value2 = new IntValue(4);
		Value result = value1.add(value2);
		assertEquals(8.5, result.getValue());
	}
	
	@Test
	public void testAddIntMoney() {
		Value value1 = new IntValue(4);
		Value value2 = new MoneyValue(4.5);
		Value result = value1.add(value2);
		assertEquals(8.5, result.getValue());
	}
	
	@Test
	public void testAddCompound() {
		Add add = new Add(new IntegerLiteral(4), new IntegerLiteral(5), -1);
		assertEquals(9, add.eval(null).getValue());
	}
	
	@Test
	public void testAddWithVariables() {
		Add add = new Add(new Variable("var1", -1), new Variable("var2", -1), -1);
		assertEquals(8.5, add.eval(valueMap).getValue());
	}
	
	@Test
	public void testBoolOperationAnd() {
		And and = new And(new BooleanLiteral(true), new BooleanLiteral(false), -1);
		assertEquals(false, and.eval(valueMap).getValue());
	}
	
	@Test
	public void testGEq() {
		GEq geq = new GEq(new IntegerLiteral(5), new IntegerLiteral(4), -1); // 16/4 >= 4 ==> true
		assertEquals(true, geq.eval(valueMap).getValue());
	}
	
	@Test 
	public void testSmallTree(){
		Div div = new Div(new IntegerLiteral(16), new IntegerLiteral(4), -1);
		assertEquals(4, div.eval(valueMap).getValue());
		
		GEq comparatorExpr = new GEq(div, new IntegerLiteral(4), -1); // 16/4 >= 4 ==> true
		assertEquals(true, comparatorExpr.eval(valueMap).getValue());
		
		And andExpr = new And (new LT(new Variable("var1", -1), new Variable("var2", -1), -1), new BooleanLiteral(true), -1); // 4.5 < 4 && true ==> false
		assertEquals(false, andExpr.eval(valueMap).getValue());
		
		Or root = new Or(andExpr, comparatorExpr, -1); // evaluates to false || true ==> true
		assertEquals(true, root.eval(valueMap).getValue());
	}


}