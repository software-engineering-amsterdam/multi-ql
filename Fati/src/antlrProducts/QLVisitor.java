// Generated from QL.g4 by ANTLR 4.4
package antlrProducts;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code PAR}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPAR(@NotNull QLParser.PARContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(@NotNull QLParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(@NotNull QLParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TimeDivExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimeDivExpr(@NotNull QLParser.TimeDivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringQL_type}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringQL_type(@NotNull QLParser.StringQL_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#questionnaire}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionnaire(@NotNull QLParser.QuestionnaireContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NormalQuestion}
	 * labeled alternative in {@link QLParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalQuestion(@NotNull QLParser.NormalQuestionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifQuestion}
	 * labeled alternative in {@link QLParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfQuestion(@NotNull QLParser.IfQuestionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(@NotNull QLParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Integerliteral}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerliteral(@NotNull QLParser.IntegerliteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanQL_type}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanQL_type(@NotNull QLParser.BooleanQL_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerQL_type}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerQL_type(@NotNull QLParser.IntegerQL_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ORExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitORExpr(@NotNull QLParser.ORExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code COMPExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCOMPExpr(@NotNull QLParser.COMPExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Booleanliteral}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanliteral(@NotNull QLParser.BooleanliteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Stringliteral}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringliteral(@NotNull QLParser.StringliteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CalculatedQuestion}
	 * labeled alternative in {@link QLParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalculatedQuestion(@NotNull QLParser.CalculatedQuestionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(@NotNull QLParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForm(@NotNull QLParser.FormContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EQUALExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEQUALExpr(@NotNull QLParser.EQUALExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfElseQuestion}
	 * labeled alternative in {@link QLParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElseQuestion(@NotNull QLParser.IfElseQuestionContext ctx);
}