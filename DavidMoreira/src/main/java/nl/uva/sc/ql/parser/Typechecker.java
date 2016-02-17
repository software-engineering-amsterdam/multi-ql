package nl.uva.sc.ql.parser;

import nl.uva.sc.ql.exceptions.SyntaxAnalysisException;
import nl.uva.sc.ql.parser.ast.ASTBoolean;
import nl.uva.sc.ql.parser.ast.ASTMoney;
import nl.uva.sc.ql.parser.ast.ASTNode;
import nl.uva.sc.ql.parser.ast.ASTString;
import nl.uva.sc.ql.parser.ast.SymbolTable;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public class Typechecker extends QLBaseVisitor<ASTNode> {

    // used to compare floating point numbers
    public static final double SMALL_VALUE = 0.00000000001;
    
    // store variables
	private SymbolTable symbolTable;
    
    public Typechecker(SymbolTable symbolTable) {
    	this.symbolTable = symbolTable;
    }
    
    // type overrides
    @Override
    public ASTNode visitBooleanType(@NotNull QLParser.BooleanTypeContext context) {
    	return new ASTBoolean();
    }
    
    @Override
    public ASTNode visitStringType(@NotNull QLParser.StringTypeContext context) {
    	return new ASTString();
    }
    
    @Override
    public ASTNode visitMoneyType(@NotNull QLParser.MoneyTypeContext context) {
    	return new ASTMoney();
    }
    
    // assignment/identifiers overrides
    @Override
    public ASTNode visitDeclareAssignVariable(@NotNull QLParser.DeclareAssignVariableContext context) {        
    	ASTNode node = this.visit(context.declaration());
        ASTNode nodeValue = this.visit(context.expression());
        
        if (node.getType() != nodeValue.getType()) {
            throw new SyntaxAnalysisException("Line "+context.EQUAL().getSymbol().getLine()+": incompatible types: "+node.getType()+" cannot be converted to "+nodeValue.getType());
        }
        
        String identifier = node.getIdentifier();
        node.setValue(nodeValue.getValue());
        
        symbolTable.addId(identifier, node); 
        return node;
    }
    
    @Override
    public ASTNode visitDeclarationVariable(@NotNull QLParser.DeclarationVariableContext context) {
        String identifier = context.IDENTIFIER().getText();
        ASTNode node = this.visit(context.type());
        
        if (symbolTable.probe(identifier) != null) {
            throw new SyntaxAnalysisException("Already defined variable: "+identifier+". Line "+context.IDENTIFIER().getSymbol().getLine());
        }
        
        node.setIdentifier(identifier);
        
        symbolTable.addId(identifier, node);
        return node;
    }
    
    @Override
    public ASTNode visitIdentifierUnity(@NotNull QLParser.IdentifierUnityContext context) {
        String identifier = context.getText();
        ASTNode node = symbolTable.lookup(identifier);
        
        if (node == null) {
            throw new SyntaxAnalysisException("Assigning undefined variable "+identifier+". Line "+context.IDENTIFIER().getSymbol().getLine());
        }
        
        return node;
    }

    // unity overrides
    @Override
    public ASTNode visitStringUnity(@NotNull QLParser.StringUnityContext context) {
        return new ASTString(context.getText());
    }

    @Override
    public ASTNode visitMoneyUnity(@NotNull QLParser.MoneyUnityContext context) {
    	return new ASTMoney(context.getText());
    }

    @Override
    public ASTNode visitBooleanUnity(@NotNull QLParser.BooleanUnityContext context) {
        return new ASTBoolean(context.getText());
    }

    // expression overrides
    @Override
    public ASTNode visitParentisisExpression(@NotNull QLParser.ParentisisExpressionContext context) {
        return this.visit(context.expression());
    }
    
    @Override
    public ASTNode visitMinusExpression(@NotNull QLParser.MinusExpressionContext context) {
        ASTNode value = this.visit(context.expression());
        
        if (value.getType() != "Money") {
        	throw new SyntaxAnalysisException("Line "+context.start.getLine()+": bad operand type "+value.getType()+" for unitary operator '-'");
        }
        
        return new ASTMoney();
    }

    @Override
    public ASTNode visitNotExpression(@NotNull QLParser.NotExpressionContext context) {
        ASTNode value = this.visit(context.expression());
        
        if (value.getType() != "Boolean") {
        	throw new SyntaxAnalysisException("Line "+context.start.getLine()+": bad operand type "+value.getType()+" for unitary operator '!'");
        }
        
        return new ASTBoolean();
    }
    
    @Override
    public ASTNode visitMultDivModExpression(@NotNull QLParser.MultDivModExpressionContext context) {
        ASTNode left = this.visit(context.expression(0));
        ASTNode right = this.visit(context.expression(1));
        
        String operator;
        
        switch (context.op.getType()) {
            case QLParser.MULT:
                operator = "*";
            case QLParser.DIV:
                operator = "/";
            case QLParser.MOD:
                operator = "%";
            default:
            	operator = "";    
        }
        
        if (left.getType() != right.getType() && left.getType() != "Money") {
        	throw new SyntaxAnalysisException("Line "+context.start.getLine()+": bad operand types for binary operator '"+operator+"' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
        }
        
        return new ASTMoney();
    }

    @Override
    public ASTNode visitAdditiveExpression(@NotNull QLParser.AdditiveExpressionContext context) {
        ASTNode left = this.visit(context.expression(0));
        ASTNode right = this.visit(context.expression(1));

        String operator;

        switch (context.op.getType()) {
            case QLParser.PLUS:
            	operator = "+";
            case QLParser.MINUS:
            	operator = "-";
            default:
            	operator = "";
        }
        
        if (left.getType() != right.getType() && left.getType() != "Money") {
        	throw new SyntaxAnalysisException("Line "+context.start.getLine()+": bad operand types for binary operator '"+operator+"' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
        }
        
        return new ASTMoney();
    }

    @Override
    public ASTNode visitRelationalExpression(@NotNull QLParser.RelationalExpressionContext context) {
        ASTNode left = this.visit(context.expression(0));
        ASTNode right = this.visit(context.expression(1));

        String operator;

        switch (context.op.getType()) {
            case QLParser.LT:
            	operator = "<";
            case QLParser.LTEQ:
            	operator = "<=";
            case QLParser.GT:
            	operator = ">";
            case QLParser.GTEQ:
            	operator = ">=";
            default:
            	operator = "";
        }
        
        if (left.getType() != right.getType() && left.getType() != "Boolean") {
        	throw new SyntaxAnalysisException("Line "+context.start.getLine()+": bad operand types for binary operator '"+operator+"' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
        }
        
        return new ASTMoney();
    }

    @Override
    public ASTNode visitEqualityExpression(@NotNull QLParser.EqualityExpressionContext context) {
        ASTNode left = this.visit(context.expression(0));
        ASTNode right = this.visit(context.expression(1));

        String operator;

        switch (context.op.getType()) {
            case QLParser.EQ:
            	operator = "==";
            case QLParser.NEQ:
            	operator = "!=";
            default:
            	operator = "";
        }
        
        if (left.getType() != right.getType() && left.getType() != "Boolean") {
        	throw new SyntaxAnalysisException("Line "+context.start.getLine()+": bad operand types for binary operator '"+operator+"' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
        }
        
        return new ASTMoney();
    }

    @Override
    public ASTNode visitAndExpression(@NotNull QLParser.AndExpressionContext context) {
        ASTNode left = this.visit(context.expression(0));
        ASTNode right = this.visit(context.expression(1));
        
        if (left.getType() != right.getType() && left.getType() != "Boolean") {
        	throw new SyntaxAnalysisException("Line "+context.start.getLine()+": bad operand types for binary operator '&&' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
        }
        
        return new ASTBoolean();
    }

    @Override
    public ASTNode visitOrExpression(@NotNull QLParser.OrExpressionContext context) {
        ASTNode left = this.visit(context.expression(0));
        ASTNode right = this.visit(context.expression(1));
        
        if (left.getType() != right.getType() && left.getType() != "Boolean") {
        	throw new SyntaxAnalysisException("Line "+context.start.getLine()+": bad operand types for binary operator '||' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
        }
        
        return new ASTBoolean();
    }

    // if override
    @Override
    public ASTNode visitIf_stat(@NotNull QLParser.If_statContext context) {
        List<QLParser.Condition_blockContext> conditions = context.condition_block();

        for(QLParser.Condition_blockContext condition : conditions) {
            ASTNode evaluated = this.visit(condition.expression());
            
            if(evaluated.getType() != "Boolean"){
            	throw new SyntaxAnalysisException("Line "+condition.start.getLine()+": incompatible types: "+evaluated.getType()+" cannot be converted to Boolean");
            }
            
            this.visit(condition.stat_block());
        }

        if(context.stat_block() != null) {
            this.visit(context.stat_block());
        }

        return new ASTNode();
    }
    
    //scope
    @Override
    public ASTNode visitBlockScope(@NotNull QLParser.BlockScopeContext context) {
    	symbolTable.enterScope();
    	ASTNode value = this.visit(context.list_statments()); 
    	symbolTable.exitScope();
        
        return value;
    }
}
