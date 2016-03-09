import * as antlr4 from 'antlr4';
import { ErrorListener } from 'antlr4/error/ErrorListener';
import { QLLexer as GeneratedLexer } from 'src/ql/generated_parser/QLLexer';
import { QLParser as GeneratedParser } from 'src/ql/generated_parser/QLParser';
import { QLVisitor as GeneratedVisitor } from 'src/ql/generated_parser/QLVisitor';
import { SemanticAnalyser } from 'src/ql/ast_semantic_analysis';
import * as ast from 'src/ql/ast';
import * as types from 'src/ql/types';
import * as values from 'src/ql/values';

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
	visitIfCase (ctx) {
		return new ast.IfNode(ctx.start.line, ctx.expr().accept(this), ctx.block(0).accept(this));
	}
	visitIfElseCase (ctx) {
		return new ast.IfElseNode(ctx.start.line, ctx.expr().accept(this), ctx.block(0).accept(this), ctx.block(1).accept(this));
	}
	visitQuestionCase (ctx) {
		return new ast.QuestionNode(ctx.start.line, ctx.STRING_LITERAL().getText().slice(1,-1), ctx.IDENTIFIER().getText(), ctx.type().accept(this));
	}
	visitExprQuestionCase (ctx) {
		return new ast.ExprQuestionNode(ctx.start.line, ctx.STRING_LITERAL().getText().slice(1,-1), ctx.IDENTIFIER().getText(), ctx.type().accept(this), ctx.expr().accept(this));
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
				return new ast.MultiplyNode(line, leftOperand, rightOperand);
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
		return new ast.LiteralNode(ctx.start.line, new types.BooleanType(), values.BooleanValue.fromString(ctx.getText()));
	}
	visitStringLiteralCase (ctx) {
		return new ast.LiteralNode(ctx.start.line, new types.StringType(), values.StringValue.fromString(ctx.getText().slice(1,-1)));
	}
	visitIntegerLiteralCase (ctx) {
		return new ast.LiteralNode(ctx.start.line, new types.IntegerType(), values.IntegerValue.fromString(ctx.getText()));
	}
	visitFloatLiteralCase (ctx) {
		return new ast.LiteralNode(ctx.start.line, new types.FloatType(), values.FloatValue.fromString(ctx.getText()));
	}
	visitMoneyLiteralCase (ctx) {
		return new ast.LiteralNode(ctx.start.line, new types.MoneyType(), values.MoneyValue.fromString(ctx.getText()));
	}
	visitType(ctx) {
		switch (ctx.start.type) {
			case GeneratedParser.TYPE_BOOLEAN:
				return new types.BooleanType();
			case GeneratedParser.TYPE_STRING:
				return new types.StringType();
			case GeneratedParser.TYPE_INTEGER:
				return new types.IntegerType();
			case GeneratedParser.TYPE_FLOAT:
				return new types.FloatType();
			case GeneratedParser.TYPE_MONEY:
				return new types.MoneyType();
			default:
				throw new Error("Unexpected type `" + ctx.getText() + "`");
		}
	}
}

export class LoggingErrorListener extends ErrorListener {
	constructor (log) {
		super();
		this.log = log;
	}
	syntaxError(recognizer, offendingSymbol, line, column, msg, e) {
		this.log.logError([line], "Syntax error (" + msg + ")");
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

export class AnalyzingQlParser {
	constructor(semanticAnalyser) {
		this.semanticAnalyser = semanticAnalyser;
	}
	parse (input, log) {
		let chars = new antlr4.InputStream(input),
			lexer = new GeneratedLexer(chars),
			tokens  = new antlr4.CommonTokenStream(lexer),
			parser = new GeneratedParser(tokens),
			errorListener = new LoggingErrorListener(log),
			visitor = new AstConversionVisitor(),
			tree,
			ast;

		lexer.removeErrorListeners();
		lexer.addErrorListener(errorListener);
		parser.buildParseTrees = true;
		parser.removeErrorListeners();
		parser.addErrorListener(errorListener);
		tree = parser.form();

		if (log.hasErrors()) {
			return null;
		}

		visitor = new AstConversionVisitor();
		ast = tree.accept(visitor);
		this.semanticAnalyser.analyse(ast, log);

		return ast;
	}
}