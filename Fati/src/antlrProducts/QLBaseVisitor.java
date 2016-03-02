// Generated from QL.g4 by ANTLR 4.4
package antlrProducts;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;


/**
 * This class provides an empty implementation of {@link QLVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class QLBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements QLVisitor<T> {
	
	 
	@Override public T visitPAR(@NotNull QLParser.PARContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitAndExpr(@NotNull QLParser.AndExprContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitIdentifier(@NotNull QLParser.IdentifierContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitTimeDivExpr(@NotNull QLParser.TimeDivExprContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitStringQL_type(@NotNull QLParser.StringQL_typeContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitQuestionnaire(@NotNull QLParser.QuestionnaireContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitNormalQuestion(@NotNull QLParser.NormalQuestionContext ctx) { return visitChildren(ctx); }
	
	 
	@Override public T visitIfQuestion(@NotNull QLParser.IfQuestionContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitAddSubExpr(@NotNull QLParser.AddSubExprContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitIntegerliteral(@NotNull QLParser.IntegerliteralContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitBooleanQL_type(@NotNull QLParser.BooleanQL_typeContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitIntegerQL_type(@NotNull QLParser.IntegerQL_typeContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitORExpr(@NotNull QLParser.ORExprContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitCOMPExpr(@NotNull QLParser.COMPExprContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitBooleanliteral(@NotNull QLParser.BooleanliteralContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitStringliteral(@NotNull QLParser.StringliteralContext ctx) { return visitChildren(ctx); }
	
	
	@Override public T visitCalculatedQuestion(@NotNull QLParser.CalculatedQuestionContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitUnaryExpr(@NotNull QLParser.UnaryExprContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitForm(@NotNull QLParser.FormContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitEQUALExpr(@NotNull QLParser.EQUALExprContext ctx) { return visitChildren(ctx); }
	
	@Override public T visitIfElseQuestion(@NotNull QLParser.IfElseQuestionContext ctx) { return visitChildren(ctx); }
}