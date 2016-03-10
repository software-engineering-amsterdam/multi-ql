import * as antlr4 from 'antlr4';
import { LoggingErrorListener } from 'src/ql/parser';
import { QLSLexer as GeneratedLexer } from 'src/qls/generated_parser/QLSLexer';
import { QLSParser as GeneratedParser } from 'src/qls/generated_parser/QLSParser';
import { QLSVisitor as GeneratedVisitor } from 'src/qls/generated_parser/QLSVisitor';
import * as ast from 'src/qls/ast';
import * as types from 'src/ql/types';
import * as values from 'src/ql/values';

export class Parser extends GeneratedVisitor {
	parse(input, log) {
		let chars = new antlr4.InputStream(input),
			lexer = new GeneratedLexer(chars),
			tokens  = new antlr4.CommonTokenStream(lexer),
			parser = new GeneratedParser(tokens),
			errorListener = new LoggingErrorListener(log),
			tree;

		lexer.removeErrorListeners();
		lexer.addErrorListener(errorListener);
		parser.buildParseTrees = true;
		parser.removeErrorListeners();
		parser.addErrorListener(errorListener);
		tree = parser.stylesheet();

		return log.hasErrors() ? null : tree.accept(this);
	}
	visitStylesheet(ctx) {
		return new ast.StylesheetNode(ctx.start.line, ctx.IDENTIFIER().getText(), ctx.stylesheetBlock().accept(this));
	}
	visitStylesheetBlock(ctx) {
		return new ast.StylesheetBlockNode(ctx.start.line, ctx.children.slice(1, -1).map((node) => node.accept(this)));
	}
	visitPageStylesheetStatement(ctx) {
		return new ast.PageNode(ctx.start.line, ctx.IDENTIFIER().getText(), ctx.pageBlock().accept(this));
	}
	visitTypeDefaultStylesheetStatement(ctx) {
		return ctx.typeDefaultStatement().accept(this);
	}
	visitPageBlock(ctx) {
		return new ast.PageBlockNode(ctx.start.line, ctx.children.slice(1, -1).map((node) => node.accept(this)));
	}
	visitSectionPageStatement(ctx) {
		return new ast.SectionNode(ctx.start.line, ctx.STRING_LITERAL().getText().slice(1, -1), ctx.pageBlock().accept(this));
	}
	visitQuestionPageStatement(ctx) {
		return new ast.QuestionNode(ctx.start.line, ctx.IDENTIFIER().getText(), ctx.styleBlock().accept(this));
	}
	visitTypeDefaultPageStatement(ctx) {
		return ctx.typeDefaultStatement().accept(this);
	}
	visitTypeDefaultStatement(ctx) {
		return new ast.TypeDefaultNode(ctx.start.line, ctx.type().accept(this), ctx.styleBlock().accept(this));
	}
	visitStyleBlock(ctx) {
		return new ast.StyleBlockNode(ctx.start.line, ctx.children.slice(1, -1).map((node) => node.accept(this)));
	}
	visitWidgetStyleStatement(ctx) {
		return ctx.widgetType().accept(this);
	}
	visitArgStyleStatement(ctx) {
		return new ast.WidgetArgNode(ctx.start.line, ctx.IDENTIFIER(), ctx.literal().accept(this));
	}
	visitSliderWidgetType(ctx) {
		return new ast.SliderWidgetNode(ctx.start.line);
	}
	visitTextWidgetType(ctx) {
		return new ast.TextWidgetNode(ctx.start.line);
	}
	visitRadioWidgetType(ctx) {
		return new ast.RadioWidgetNode(ctx.start.line, ctx.valueOptionList().accept(this));
	}
	visitValueOptionList(ctx) {
		return ctx.valueOptions().accept(this);
	}
	visitMoreValueOptions(ctx) {
		return [ctx.STRING_LITERAL().getText().slice(1, -1)].concat(ctx.valueOptions().accept(this));
	}
	visitLastValueOption(ctx) {
		return [ctx.STRING_LITERAL().getText().slice(1, -1)];
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
		switch (ctx.getText()) {
			case 'boolean':
				return new types.BooleanType();
			case 'string':
				return new types.StringType();
			case 'integer':
				return new types.IntegerType();
			case 'float':
				return new types.FloatType();
			case 'money':
				return new types.MoneyType();
			default:
				throw new Error("Unexpected type `" + ctx.getText() + "`");
		}
	}
}