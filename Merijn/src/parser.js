import * as antlr4 from 'antlr4';
import { ErrorListener } from 'antlr4/error/ErrorListener';
import { QLLexer as GeneratedLexer } from 'src/generated_parser/QLLexer';
import { QLParser as GeneratedParser } from 'src/generated_parser/QLParser';
import { QLVisitor as GeneratedVisitor } from 'src/generated_parser/QLVisitor';
import { SemanticAnalyser } from 'src/ast_semantic_analysis';
import * as ast from 'src/ast';
import { LineError } from 'src/error';

// Use a visitor to convert the parse context into an ast
class AstConversionVisitor extends GeneratedVisitor {
	visitForm(ctx) {
		return new ast.FormNode(ctx.start.line, ctx.STRING_LITERAL().getText().slice(1,-1), ctx.block().accept(this));
	}
	visitBlock(ctx) {
		let statement_nodes = [];
		for (var statement of ctx.children.slice(1, -1)) {
			statement_nodes.push(statement.accept(this));
		}
		return new ast.BlockNode(ctx.start.line, statement_nodes);
	}
	visitIfStatementCase (ctx) {
		return ctx.if_().accept(this);
	}
	visitQuestionStatementCase (ctx) {
		return ctx.question().accept(this);
	}
	visitIf_ (ctx) {
		let elseBlock = ctx.block(1);
		return new ast.IfNode(ctx.start.line, ctx.expr().accept(this), ctx.block(0).accept(this), elseBlock === null ? null : elseBlock.accept(this));
	}
	visitInputQuestionCase (ctx) {
		return new ast.InputQuestionNode(ctx.start.line, ctx.STRING_LITERAL().getText().slice(1,-1), ctx.IDENTIFIER().getText(), ctx.type().accept(this));
	}
	visitExprQuestionCase (ctx) {
		return new ast.ExprQuestionNode(ctx.start.line, ctx.STRING_LITERAL().getText().slice(1,-1), ctx.IDENTIFIER().getText(), ctx.expr().accept(this));
	}
	visitParenExprCase (ctx) {
		return ctx.expr().accept(this);
	}
	visitLiteralExprCase (ctx) {
		return ctx.literal().accept(this);
	}
	visitIdentifierExprCase (ctx) {
		return new ast.IdentifierNode(ctx.start.line, ctx.getText());
	}

	visitUnaryPrefixExprCase(ctx) {
		let line = ctx.start.line,
			operand = ctx.expr().accept(this);

		switch (ctx.children[0].symbol.type) {
			case GeneratedParser.MINUS:
				return new ast.NegationNode(line, operand);
			case GeneratedParser.NOT:
				return new ast.NotNode(line, operand);
			default:
				throw new Error("Unexpected unary prefix operation `" + ctx.children[1].getText() + "`");
		}
	}
	visitInfixExprCase(ctx) {
		let line = ctx.start.line,
			leftOperand = ctx.expr(0).accept(this),
			rightOperand = ctx.expr(1).accept(this);

		switch (ctx.children[1].symbol.type) {
			case GeneratedParser.MUL:
				return new ast.MuliplyNode(line, leftOperand, rightOperand);
			case GeneratedParser.DIV:
				return new ast.DivideNode(line, leftOperand, rightOperand);
			case GeneratedParser.PLUS:
				return new ast.AddNode(line, leftOperand, rightOperand);
			case GeneratedParser.MINUS:
				return new ast.SubtractNode(line, leftOperand, rightOperand);
			case GeneratedParser.GT:
				return new ast.GreaterNode(line, leftOperand, rightOperand);
			case GeneratedParser.GT_EQ:
				return new ast.GreaterEqualNode(line, leftOperand, rightOperand);
			case GeneratedParser.LT:
				return new ast.LessNode(line, leftOperand, rightOperand);
			case GeneratedParser.LT_EQ:
				return new ast.LessEqualNode(line, leftOperand, rightOperand);
			case GeneratedParser.EQ:
				return new ast.EqualNode(line, leftOperand, rightOperand);
			case GeneratedParser.NOT_EQ:
				return new ast.NotEqualNode(line, leftOperand, rightOperand);
			case GeneratedParser.AND:
				return new ast.AndNode(line, leftOperand, rightOperand);
			case GeneratedParser.OR:
				return new ast.OrNode(line, leftOperand, rightOperand);
			default:
				throw new Error("Unexpected infix operation `" + ctx.children[1].getText() + "`");
		}
	}
	visitBooleanLiteralCase (ctx) {
		return ctx.booleanLiteral().accept(this);
	}
	visitStringLiteralCase (ctx) {
		return new ast.StringLiteralNode(ctx.start.line, ctx.getText().slice(1,-1));
	}
	visitIntegerLiteralCase (ctx) {
		return new ast.IntegerLiteralNode(ctx.start.line, parseInt(ctx.getText(), 10));
	}
	visitFloatLiteralCase (ctx) {
		return new ast.FloatLiteralNode(ctx.start.line, parseFloat(ctx.getText()));
	}
	visitMoneyLiteralCase (ctx) {
		return new ast.MoneyLiteralNode(ctx.start.line, ctx.getText());
	}
	visitBooleanLiteral(ctx) {
		let value;
		switch (ctx.start.type) {
			case GeneratedParser.BOOLEAN_TRUE:
				value = true;
				break;
			case GeneratedParser.BOOLEAN_FALSE:
				value = false;
				break;
			default:
				throw new Error("Unexpected boolean literal `" + ctx.getText() + "`");
		}
		return new ast.BooleanLiteralNode(ctx.start.line, value);
	}
	visitType(ctx) {
		switch (ctx.start.type) {
			case GeneratedParser.TYPE_BOOLEAN:
				return ast.TYPE_BOOLEAN;
			case GeneratedParser.TYPE_STRING:
				return ast.TYPE_STRING;
			case GeneratedParser.TYPE_INTEGER:
				return ast.TYPE_INTEGER;
			case GeneratedParser.TYPE_FLOAT:
				return ast.TYPE_FLOAT;
			case GeneratedParser.TYPE_MONEY:
				return ast.TYPE_FLOAT;
			default:
				throw new Error("Unexpected type `" + ctx.getText() + "`");
		}
	}
}

class AggregatingErrorListener extends ErrorListener {
	constructor () {
		super();
		this.errors = [];
	}
	syntaxError(recognizer, offendingSymbol, line, column, msg, e) {
		this.errors.push(new LineError(line, "Syntax error: " + msg));
	}

	// the rest of these listeners should no be called, so crash
	reportAmbiguity(recognizer, dfa, startIndex, stopIndex, exact, ambigAlts, configs) {
		throw new Error("Ambiguity reported");
	}
	reportAttemptingFullContext(recognizer, dfa, startIndex, stopIndex, conflictingAlts, configs) {
		throw new Error("Full context attempt reported");
	}
	reportContextSensitivity(recognizer, dfa, startIndex, stopIndex, prediction, configs) {
		throw new Error("Context sensitivity reported");
	}
}

export class ParseResult {
	constructor (ast, errors) {
		this.ast = ast;
		this.errors = errors;
	}
}

export class AnalyzingQlParser {
	constructor(semanticAnalyser) {
		this.semanticAnalyser = semanticAnalyser;
	}
	parse (input) {
		let errors,
			chars = new antlr4.InputStream(input),
			lexer = new GeneratedLexer(chars),
			tokens  = new antlr4.CommonTokenStream(lexer),
			parser = new GeneratedParser(tokens),
			errorListener = new AggregatingErrorListener(),
			visitor = new AstConversionVisitor(),
			tree,
			ast;

		lexer.removeErrorListeners();
		lexer.addErrorListener(errorListener);
		parser.buildParseTrees = true;
		parser.removeErrorListeners();
		parser.addErrorListener(errorListener);
		tree = parser.form();

		errors = errorListener.errors;
		if (errors.length > 0) {
			return new ParseResult(null, errors);
		}

		visitor = new AstConversionVisitor();
		ast = tree.accept(visitor);
		errors = this.semanticAnalyser.analyse(ast);

		return new ParseResult(ast, errors);
	}
}