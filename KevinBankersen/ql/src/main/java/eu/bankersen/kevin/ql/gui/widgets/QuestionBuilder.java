package eu.bankersen.kevin.ql.gui.widgets;

import java.util.LinkedList;
import java.util.List;

import eu.bankersen.kevin.ql.ast.AbstractVisitor;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.NormalQuestion;
import eu.bankersen.kevin.ql.typechecker.symboltable.Symbol;
import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;

public class QuestionBuilder extends AbstractVisitor {
    
    private final List<QuestionWidget> questionWidgets;
    private final SymbolTable symbolTable;
    
    public QuestionBuilder(Form form, SymbolTable symboltable) {
	this.questionWidgets = new LinkedList<>();
	this.symbolTable = symboltable;
	form.accept(this, symbolTable);
    }

    public List<QuestionWidget> getWidgets() {
	return questionWidgets;
    }
    @Override
    public void visit(NormalQuestion o) {
	Symbol symbol = symbolTable.getSymbol(o.name());
	buildWidget(new Symbol(symbol.isComputed(), symbol.getName(), o.text(), symbol.getType(), symbol.getValue()));
    }

    @Override
    public void visit(ComputedQuestion o) {
	Symbol symbol = symbolTable.getSymbol(o.name());
	buildWidget(new Symbol(symbol.isComputed(), symbol.getName(), o.text(), symbol.getType(), symbol.getValue()));	
    }
    
    private void buildWidget(Symbol symbol) {
	questionWidgets.add(new QuestionWidget(symbol));
    }
}
