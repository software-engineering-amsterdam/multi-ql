package eu.bankersen.kevin.ql.oldcode;

import java.util.HashMap;
import java.util.Map;

import eu.bankersen.kevin.ql.ast.Identifier;
import eu.bankersen.kevin.ql.ast.Literal;
import eu.bankersen.kevin.ql.ast.Variable;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.logic.And;
import eu.bankersen.kevin.ql.ast.expr.logic.Eq;
import eu.bankersen.kevin.ql.ast.expr.logic.GEq;
import eu.bankersen.kevin.ql.ast.expr.logic.GT;
import eu.bankersen.kevin.ql.ast.expr.logic.LEq;
import eu.bankersen.kevin.ql.ast.expr.logic.LT;
import eu.bankersen.kevin.ql.ast.expr.logic.NEq;
import eu.bankersen.kevin.ql.ast.expr.logic.Not;
import eu.bankersen.kevin.ql.ast.expr.logic.Or;
import eu.bankersen.kevin.ql.ast.expr.math.Add;
import eu.bankersen.kevin.ql.ast.expr.math.Div;
import eu.bankersen.kevin.ql.ast.expr.math.Mul;
import eu.bankersen.kevin.ql.ast.expr.math.Neg;
import eu.bankersen.kevin.ql.ast.expr.math.Pos;
import eu.bankersen.kevin.ql.ast.expr.math.Sub;
import eu.bankersen.kevin.ql.ast.form.Body;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.stat.IFStat;
import eu.bankersen.kevin.ql.ast.stat.Question;
import eu.bankersen.kevin.ql.ast.stat.Statement;
import eu.bankersen.kevin.ql.context.Symbol;

public class BuildSymbolTableVisitor implements QLVisitor {
    
    private final Map<String, Symbol> symbolTable;
    
    public BuildSymbolTableVisitor() {
	symbolTable = new HashMap<>();
    }

    @Override
    public void visit(Form data) {
	visit(data.body());
    }

    @Override
    public void visit(Body data) {
	System.out.println("Inside body");
	data.statements().forEach(statement -> visit(statement));
    }

    @Override
    public void visit(Statement data) {
	System.out.println("Statement");
	if (data.isQuestion()) {
	    visit((Question) data);
	} else {
	    visit((IFStat) data);
	}
    }
    
    @Override
    public void visit(IFStat data) {
	System.out.println("IF Statement");
	
    }

    @Override
    public void visit(Question data) {
	System.out.println("Question Statement");
	visit(data.variable());
    }

    @Override
    public void visit(Variable data) {
	System.out.println("Variable");
	visit(data.expr());
	
    }

    private void visit(Expr expr) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(Identifier data) {
	// TODO Auto-generated method stub
	System.out.println("identifier");
	
    }

    @Override
    public void visit(Literal data) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(And data) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(Eq data) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(GEq data) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(GT data) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(LEq data) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(LT data) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(NEq data) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(Not data) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(Or data) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(Add data) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(Div data) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(Mul data) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(Neg data) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(Pos data) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void visit(Sub data) {
	// TODO Auto-generated method stub
	
    }

}
