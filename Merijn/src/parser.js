import * as antlr4 from 'antlr4';
import { QLLexer as GeneratedLexer } from 'src/generated_parser/QLLexer';
import { QLParser as GeneratedParser } from 'src/generated_parser/QLParser';
import { QLVisitor as GeneratedVisitor } from 'src/generated_parser/QLVisitor';
import * as ast from 'src/ast';

// Use a visitor to convert the parse context into an ast
class AstConversionVisitor extends GeneratedVisitor {
	visitForm(ctx) {
		return new ast.FormNode(ctx.start.line, ctx.block().accept(this));
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
	visitQuestion (ctx) {
		return new ast.QuestionNode(ctx.start.line, ctx.STRING_LITERAL().getText().slice(1,-1), ctx.IDENTIFIER().getText(), ctx.type().accept(this));
	}
	visitParenExprCase (ctx) {
		return ctx.expr().accept(this);
	}
	visitLiteralExprCase (ctx) {
		return ctx.literal().accept(this);
	}
	visitIdentifierExprCase (ctx) {
		return new ast.Identifier(ctx.getText());
	}
	visitUnaryPrefixExprCase (ctx) {
		return new ast.UnaryPrefixNode(ctx.start.line, ctx.children[0].getText(), ctx.expr().accept(this));
	}
	visitInfixExprCase (ctx) {
		let children = ctx.children;
		return new ast.InfixNode(ctx.start.line, children[0].accept(this), children[1], children[2].accept(this));
	}
	visitBooleanLiteralCase (ctx) {
		return ctx.booleanLiteral().accept(this);
	}
	visitStringLiteralCase (ctx) {
		return new ast.LiteralNode(ctx.start.line, ctx.getText().slice(1,-1), ast.TYPE_STRING);
	}
	visitIntegerLiteralCase (ctx) {
		return new ast.LiteralNode(ctx.start.line, parseInt(ctx.getText(), 10), ast.TYPE_INTEGER);
	}
	visitFloatLiteralCase (ctx) {
		return new ast.LiteralNode(ctx.start.line, parseFloat(ctx.getText()), ast.TYPE_FLOAT);
	}
	visitMoneyLiteralCase (ctx) {
		return new ast.LiteralNode(ctx.start.line, ctx.getText(), ast.TYPE_MONEY);
	}
	visitBooleanTrueCase (ctx) {
		return new ast.LiteralNode(ctx.start.line, true, ast.TYPE_BOOLEAN);
	}
	visitBooleanFalseCase (ctx) {
		return new ast.LiteralNode(ctx.start.line, false, ast.TYPE_BOOLEAN);
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

export class QlParser {
	parse (input) {
		let chars = new antlr4.InputStream(input);
		let lexer = new GeneratedLexer(chars);
		let tokens  = new antlr4.CommonTokenStream(lexer);
		let parser = new GeneratedParser(tokens);
		parser.buildParseTrees = true;
		let tree = parser.form();
		let visitor = new AstConversionVisitor();
		return tree.accept(visitor);
	}
}