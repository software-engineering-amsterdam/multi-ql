package nl.uva.sc.ql.parser;

import nl.uva.sc.ql.exceptions.InterpreterException;
import nl.uva.sc.ql.parser.ast.ASTBoolean;
import nl.uva.sc.ql.parser.ast.ASTMoney;
import nl.uva.sc.ql.parser.ast.ASTNode;
import nl.uva.sc.ql.parser.ast.ASTString;
import nl.uva.sc.ql.parser.ast.SymbolTable;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public class Interpreter extends QLBaseVisitor<ASTNode> {

    // used to compare floating point numbers
    public static final double SMALL_VALUE = 0.00000000001;
    
    // store variables
	private SymbolTable symbolTable;
    
    public Interpreter(SymbolTable symbolTable) {
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
        ASTNode value = this.visit(context.expression());
        
        String identifier = node.getIdentifier();
        node.setValue(value);
        
        symbolTable.addId(identifier, node); 
        return node;
    }
    
    @Override
    public ASTNode visitDeclarationVariable(@NotNull QLParser.DeclarationVariableContext context) {
        String identifier = context.IDENTIFIER().getText();
        ASTNode node = this.visit(context.type());
        
        if (symbolTable.probe(identifier) != null) {
            throw new InterpreterException("Already defined variable: " + identifier + " in line " + context.IDENTIFIER().getSymbol().getLine());
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
            throw new InterpreterException("No such variable: " + identifier + " in line " + context.IDENTIFIER().getSymbol().getLine());
        }
        
        return node;
    }

    // unity overrides
    @Override
    public ASTNode visitStringUnity(@NotNull QLParser.StringUnityContext context) {
        String string = context.getText();
        // strip quotes
        string = string.substring(1, string.length() - 1).replace("\"\"", "\"");
        return new ASTString(string);
    }

    @Override
    public ASTNode visitMoneyUnity(@NotNull QLParser.MoneyUnityContext context) {
    	String string = context.getText();
    	if (string.contains(".")){
    		return new ASTMoney(Double.valueOf(string));
    	} else {
    		return new ASTMoney(Integer.valueOf(string));
    	}
    }

    @Override
    public ASTNode visitBooleanUnity(@NotNull QLParser.BooleanUnityContext context) {
        return new ASTBoolean(Boolean.valueOf(context.getText()));
    }

    // expression overrides
    @Override
    public ASTNode visitParentisisExpression(@NotNull QLParser.ParentisisExpressionContext context) {
        return this.visit(context.expression());
    }
    
    @Override
    public ASTNode visitMinusExpression(@NotNull QLParser.MinusExpressionContext context) {
        ASTNode value = this.visit(context.expression());
        return new ASTMoney(-value.asDouble());
    }

    @Override
    public ASTNode visitNotExpression(@NotNull QLParser.NotExpressionContext context) {
        ASTNode value = this.visit(context.expression());
        return new ASTBoolean(!value.asBoolean());
    }
    
    @Override
    public ASTNode visitMultDivModExpression(@NotNull QLParser.MultDivModExpressionContext context) {
        ASTNode left = this.visit(context.expression(0));
        ASTNode right = this.visit(context.expression(1));

        switch (context.op.getType()) {
            case QLParser.MULT:
                return new ASTMoney(left.asDouble() * right.asDouble());
            case QLParser.DIV:
                return new ASTMoney(left.asDouble() / right.asDouble());
            case QLParser.MOD:
                return new ASTMoney(left.asDouble() % right.asDouble());
            default:
                throw new InterpreterException("Unknown operator: " + QLParser.tokenNames[context.op.getType()] + " in line " + context.op.getLine());
        }
    }

    @Override
    public ASTNode visitAdditiveExpression(@NotNull QLParser.AdditiveExpressionContext context) {
        ASTNode left = this.visit(context.expression(0));
        ASTNode right = this.visit(context.expression(1));

        switch (context.op.getType()) {
            case QLParser.PLUS:
                return left.isDouble() && right.isDouble() ?
                        new ASTMoney(left.asDouble() + right.asDouble()) :
                        new ASTMoney(left.asString() + right.asString());
            case QLParser.MINUS:
                return new ASTMoney(left.asDouble() - right.asDouble());
            default:
                throw new InterpreterException("Unknown operator: " + QLParser.tokenNames[context.op.getType()] + " in line " + context.op.getLine());
        }
    }

    @Override
    public ASTNode visitRelationalExpression(@NotNull QLParser.RelationalExpressionContext context) {
        ASTNode left = this.visit(context.expression(0));
        ASTNode right = this.visit(context.expression(1));

        switch (context.op.getType()) {
            case QLParser.LT:
                return new ASTBoolean(left.asDouble() < right.asDouble());
            case QLParser.LTEQ:
                return new ASTBoolean(left.asDouble() <= right.asDouble());
            case QLParser.GT:
                return new ASTBoolean(left.asDouble() > right.asDouble());
            case QLParser.GTEQ:
                return new ASTBoolean(left.asDouble() >= right.asDouble());
            default:
                throw new InterpreterException("Unknown operator: " + QLParser.tokenNames[context.op.getType()] + " in line " + context.op.getLine());
        }
    }

    @Override
    public ASTNode visitEqualityExpression(@NotNull QLParser.EqualityExpressionContext context) {
        ASTNode left = this.visit(context.expression(0));
        ASTNode right = this.visit(context.expression(1));

        switch (context.op.getType()) {
            case QLParser.EQ:
                return left.isDouble() && right.isDouble() ?
                		new ASTBoolean(Math.abs(left.asDouble() - right.asDouble()) < SMALL_VALUE) :
                        new ASTBoolean(left.equals(right));
            case QLParser.NEQ:
                return left.isDouble() && right.isDouble() ?
                        new ASTBoolean(Math.abs(left.asDouble() - right.asDouble()) >= SMALL_VALUE) :
                        new ASTBoolean(!left.equals(right));
            default:
                throw new InterpreterException("Unknown operator: " + QLParser.tokenNames[context.op.getType()] + " in line " + context.op.getLine());
        }
    }

    @Override
    public ASTNode visitAndExpression(@NotNull QLParser.AndExpressionContext context) {
        ASTNode left = this.visit(context.expression(0));
        ASTNode right = this.visit(context.expression(1));
        return new ASTBoolean(left.asBoolean() && right.asBoolean());
    }

    @Override
    public ASTNode visitOrExpression(@NotNull QLParser.OrExpressionContext context) {
        ASTNode left = this.visit(context.expression(0));
        ASTNode right = this.visit(context.expression(1));
        return new ASTBoolean(left.asBoolean() || right.asBoolean());
    }

    // if override
    @Override
    public ASTNode visitIf_stat(@NotNull QLParser.If_statContext context) {
        List<QLParser.Condition_blockContext> conditions = context.condition_block();
        boolean evaluatedBlock = false;

        for(QLParser.Condition_blockContext condition : conditions) {
            ASTNode evaluated = this.visit(condition.expression());
            if(evaluated.asBoolean()) {
                evaluatedBlock = true;
                // evaluate this block whose expression==true
                this.visit(condition.stat_block());
                break;
            }
        }

        if(!evaluatedBlock && context.stat_block() != null) {
            // evaluate the else-stat_block (if present == not null)
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
