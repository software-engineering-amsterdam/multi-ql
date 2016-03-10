package eu.bankersen.kevin.ql.typechecker.symboltable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import eu.bankersen.kevin.ql.ast.AbstractVisitor;
import eu.bankersen.kevin.ql.ast.form.Body;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.ast.stat.NormalQuestion;
import eu.bankersen.kevin.ql.ast.type.Type;
import eu.bankersen.kevin.ql.typechecker.errors.AllreadyDeclaredError;
import eu.bankersen.kevin.ql.typechecker.errors.TypeCheckError;
import eu.bankersen.kevin.ql.typechecker.warnings.AllreadyDeclaredWarning;
import eu.bankersen.kevin.ql.typechecker.warnings.TypeCheckWarning;

public class SymbolTableBuilder extends AbstractVisitor {

    private final Map<String, List<Symbol>> symbolTable;
    private final List<TypeCheckError> errorList;
    private final List<TypeCheckWarning> warningList;

    public SymbolTableBuilder(Form form) {
	this.symbolTable = new LinkedHashMap<>();
	this.errorList = new ArrayList<>();
	this.warningList = new ArrayList<>();
	form.accept(this, symbolTable);
    }
    
    public SymbolTable getSymbolTable() {
	return convertRawSymboTable(symbolTable);
    }

    public List<TypeCheckError> getErrors() {
	return errorList;
    }
    
    public List<TypeCheckWarning> getWarnings() {
	return warningList;
    }

    @Override
    public void visit(NormalQuestion o) {
	addSymbol(false, o.name(), o.text(), o.type());
    }

    @Override
    public void visit(ComputedQuestion o) {
	addSymbol(true, o.name(), o.text(), o.type());
    }

    private void addSymbol(Boolean computed, String name, String question, Type type) {
	Symbol symbol = new Symbol(computed, name, question, type, null);
	List<Symbol> list;

	if (symbolTable.containsKey(name)) {
	    list = symbolTable.get(name);
	    list.add(symbol);
	} else {
	    list = new ArrayList<>();
	    list.add(symbol);
	}
	symbolTable.put(name, list);
    }

    private SymbolTable convertRawSymboTable(Map<String, List<Symbol>> rawSymbolTable) {

	SymbolTable table = new SymbolTable();
	Symbol symbol;
	for (String key : rawSymbolTable.keySet()) {
	    if (rawSymbolTable.get(key).size() == 1) {
		symbol = rawSymbolTable.get(key).get(0);
	    } else {
		symbol = analyzeQuestions(rawSymbolTable.get(key));
	    }
	    table.addSymbol(key, symbol);
	}
	return  table;
    }  
    
    private Symbol analyzeQuestions(List<Symbol> questions) {
	Symbol question1 = questions.get(0);
	
	for (int i = 1; i < questions.size(); i++) {
	    Symbol question2 = questions.get(i);
	    
	    if (question2.getType().isSimilar(question1.getType())) {
		if (!question2.getQuestion().equals(question1.getQuestion())) {
		warningList.add(new AllreadyDeclaredWarning(0, question1, question2));
		}
	    } else {
		errorList.add(new AllreadyDeclaredError(0, question1, question2));
	    }
	}
	return question1;
    }
}