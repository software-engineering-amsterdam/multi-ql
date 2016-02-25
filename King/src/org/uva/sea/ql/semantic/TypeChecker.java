package org.uva.sea.ql.semantic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.ql.ast.ValueType;
import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.VarIdentifier;
import org.uva.sea.ql.ast.TaxForm.Block;
import org.uva.sea.ql.ast.TaxForm.Form;
import org.uva.sea.ql.ast.TaxForm.IFblock;
import org.uva.sea.ql.ast.TaxForm.Question;
import org.uva.sea.ql.ast.expr.Add;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.antlr.QLParser.FileContext;

public class TypeChecker implements QLNodeVisitor{
	SymbolTable symTable;
	Result result;
	List<String> lableNames = new ArrayList<>();
	public TypeChecker(Form form) {
		result = new Result();
		symTable = new SymbolTable();
		form.accept(this);
		
	}
	@Override
	public void visit(Block block) {
		
		for (Question q : block.getQuestions()) {
			q.accept(this);
		}
		
		for (IFblock ib: block.getStatements()) {
			ib.accept(this);
		}
		
	}
	@Override
	public void visit(VarDeclaration node) {
		/*String variableName = node.getIdentifier().getName();
		if (symTable.contains(variableName)) {
			System.err.println("Duplicate variable. The variable '" + variableName + "' has already been declared");
			return;
		}
		symTable.add(variableName, node.getType().getType());*/
		
	}
	@Override
	public void visit(Form form) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(VarIdentifier varId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(Add add) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(IFblock statement) {
		for (Question ibq : statement.getBody().getQuestions()) {
			ibq.accept(this);
		}
	}
	@Override
	public void visit(Question question) {
		String lableName = question.getText();
		String variableName = question.getVariableId().getIdentifier().getName();
		Type type = question.getVariableId().getType();
		if(checkDuplicateDeclaration(variableName,type)){
			String msg = "The variable '" + variableName + "' has been declared multiple time with different type";
			//To do later add the number of time that variable was declared
			result.addError(variableName, msg);
		}else{
			symTable.add(variableName,type );
		}
		
		if(checkDuplicateLables(lableName)){
			String msg = "The lable '" + lableName + "' has been used more than once";
			
			result.addWarning(lableName, msg);
		}else{
			lableNames.add(lableName);
		}
		
	}
	
	private Boolean checkDuplicateDeclaration(String variableName,Type type) {
		boolean isDuplicateVariable = false;
		
		if (symTable.contains(variableName)) {
			if(type.equals(symTable.lookupType(variableName)) == false){
				isDuplicateVariable = true;
			}
			
		}
		return isDuplicateVariable;
	}
	
	private Boolean checkDuplicateLables(String lableName) {
		boolean isDuplicatelableName = false;
		
		if (lableNames.contains(lableName)) {
				isDuplicatelableName = true;
			
		}
		return isDuplicatelableName;
	}

	
	public static void main(String[] args) throws IOException {
		//Loading the DSL script into the ANTLR stream.
	    ANTLRInputStream input = new ANTLRFileStream(new File("resources/questionaire.gr").getPath());

	    //Passing the input to the lexer to create tokens
	    QLLexer lexer = new QLLexer(input);

	    CommonTokenStream tokens = new CommonTokenStream(lexer);

	    //Passing the tokens to the parser to create the parse tree. 
	    QLParser parser = new QLParser(tokens);
	   
	    FileContext fileContext = parser.file();
	    Form ast = fileContext.form(0).result;
	    //System.out.println(ast);
	    TypeChecker typeChecker = new TypeChecker(ast);
	    typeChecker.result.print();
		
	}

}
