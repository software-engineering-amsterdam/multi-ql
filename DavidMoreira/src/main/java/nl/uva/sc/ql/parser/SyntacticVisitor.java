package nl.uva.sc.ql.parser;

import nl.uva.sc.ql.parser.ast.SymbolTable;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public class SyntacticVisitor extends QLBaseVisitor<Value> {

    // used to compare floating point numbers
    public static final double SMALL_VALUE = 0.00000000001;
    
    // store variables
	private SymbolTable symbolTable;
    
    public SyntacticVisitor(SymbolTable symbolTable) {
    	this.symbolTable = symbolTable;
    }
    
    // type overrides
    @Override
    public Value visitBooleanType(@NotNull QLParser.BooleanTypeContext ctx) {
    	return new Value(new Boolean(null));
    }
    
    @Override
    public Value visitStringType(@NotNull QLParser.StringTypeContext ctx) {
    	return new Value(new String());
    }
    
    @Override
    public Value visitMoneyType(@NotNull QLParser.MoneyTypeContext ctx) {
    	return new Value(new Integer(null));
    }
    
    // assignment/identifiers overrides
    @Override
    public Value visitAssignmentVariable(QLParser.AssignmentVariableContext ctx) {
        String id = ctx.declaration().getText();
        System.out.println("visitAssignmentVariable: " + id);
        // TODO: check if type and expr are the same type
        Value value = this.visit(ctx.expr());
        
        return symbolTable.addId(id, value);        
    }

    @Override
    public Value visitIdentifierUnity(QLParser.IdentifierUnityContext ctx) {
        String id = ctx.getText();
        Value value = symbolTable.lookup(id);
        if(value == null) {
            throw new RuntimeException("no such variable: " + id + " in line " + ctx.start.getLine());
        }
        return value;
    }
    
    @Override
    public Value visitDeclarationVariable(QLParser.DeclarationVariableContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        Value value = this.visit(ctx.type());
        
        return symbolTable.addId(id, value);
    }

    // unity overrides
    @Override
    public Value visitStringUnity(QLParser.StringUnityContext ctx) {
        String str = ctx.getText();
        // strip quotes
        str = str.substring(1, str.length() - 1).replace("\"\"", "\"");
        return new Value(str);
    }

    @Override
    public Value visitMoneyUnity(QLParser.MoneyUnityContext ctx) {
    	String str = ctx.getText();
    	if (str.contains(".")){
    		return new Value(Double.valueOf(ctx.getText()));
    	} else {
    		return new Value(Integer.valueOf(ctx.getText()));
    	}
    }

    @Override
    public Value visitBooleanUnity(QLParser.BooleanUnityContext ctx) {
        return new Value(Boolean.valueOf(ctx.getText()));
    }

    // expression overrides
    @Override
    public Value visitParentisisExpression(QLParser.ParentisisExpressionContext ctx) {
        return this.visit(ctx.expr());
    }

    /*
    @Override
    public Value visitPowExpr(QLParser.PowExprContext ctx) {
        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));
        return new Value(Math.pow(left.asDouble(), right.asDouble()));
    }
    */
    
    /*
    @Override
    public Value visitUnaryMinusExpr(QLParser.UnaryMinusExprContext ctx) {
        Value value = this.visit(ctx.expr());
        return new Value(-value.asDouble());
    }
    */

    @Override
    public Value visitNotExpression(QLParser.NotExpressionContext ctx) {
        Value value = this.visit(ctx.expr());
        return new Value(!value.asBoolean());
    }
    
    @Override
    public Value visitMultDivModExpression(@NotNull QLParser.MultDivModExpressionContext ctx) {

        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));

        switch (ctx.op.getType()) {
            case QLParser.MULT:
                return new Value(left.asDouble() * right.asDouble());
            case QLParser.DIV:
                return new Value(left.asDouble() / right.asDouble());
            case QLParser.MOD:
                return new Value(left.asDouble() % right.asDouble());
            default:
                throw new RuntimeException("unknown operator: " + QLParser.tokenNames[ctx.op.getType()]);
        }
    }

    @Override
    public Value visitAdditiveExpression(@NotNull QLParser.AdditiveExpressionContext ctx) {

        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));

        switch (ctx.op.getType()) {
            case QLParser.PLUS:
                return left.isDouble() && right.isDouble() ?
                        new Value(left.asDouble() + right.asDouble()) :
                        new Value(left.asString() + right.asString());
            case QLParser.MINUS:
                return new Value(left.asDouble() - right.asDouble());
            default:
                throw new RuntimeException("unknown operator: " + QLParser.tokenNames[ctx.op.getType()]);
        }
    }

    @Override
    public Value visitRelationalExpression(@NotNull QLParser.RelationalExpressionContext ctx) {

        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));

        switch (ctx.op.getType()) {
            case QLParser.LT:
                return new Value(left.asDouble() < right.asDouble());
            case QLParser.LTEQ:
                return new Value(left.asDouble() <= right.asDouble());
            case QLParser.GT:
                return new Value(left.asDouble() > right.asDouble());
            case QLParser.GTEQ:
                return new Value(left.asDouble() >= right.asDouble());
            default:
                throw new RuntimeException("unknown operator: " + QLParser.tokenNames[ctx.op.getType()]  + " in line " + ctx.start.getLine());
        }
    }

    @Override
    public Value visitEqualityExpression(@NotNull QLParser.EqualityExpressionContext ctx) {

        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));

        switch (ctx.op.getType()) {
            case QLParser.EQ:
                return left.isDouble() && right.isDouble() ?
                        new Value(Math.abs(left.asDouble() - right.asDouble()) < SMALL_VALUE) :
                        new Value(left.equals(right));
            case QLParser.NEQ:
                return left.isDouble() && right.isDouble() ?
                        new Value(Math.abs(left.asDouble() - right.asDouble()) >= SMALL_VALUE) :
                        new Value(!left.equals(right));
            default:
                throw new RuntimeException("unknown operator: " + QLParser.tokenNames[ctx.op.getType()] + " in line " + ctx.start.getLine());
        }
    }

    @Override
    public Value visitAndExpression(QLParser.AndExpressionContext ctx) {
        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));
        return new Value(left.asBoolean() && right.asBoolean());
    }

    @Override
    public Value visitOrExpression(QLParser.OrExpressionContext ctx) {
        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));
        return new Value(left.asBoolean() || right.asBoolean());
    }

    // if override
    @Override
    public Value visitIf_stat(QLParser.If_statContext ctx) {

        List<QLParser.Condition_blockContext> conditions =  ctx.condition_block();

        boolean evaluatedBlock = false;

        for(QLParser.Condition_blockContext condition : conditions) {

            Value evaluated = this.visit(condition.expr());

            if(evaluated.asBoolean()) {
                evaluatedBlock = true;
                // evaluate this block whose expr==true
                this.visit(condition.stat_block());
                break;
            }
        }

        if(!evaluatedBlock && ctx.stat_block() != null) {
            // evaluate the else-stat_block (if present == not null)
            this.visit(ctx.stat_block());
        }

        return Value.VOID;
    }
    
    //scope
    @Override
    public Value visitBlockScope(QLParser.BlockScopeContext ctx) {
    	symbolTable.enterScope();
    	
    	Value value = this.visit(ctx.list_statments());
        
    	symbolTable.exitScope();
        
        return value;
    }
}
