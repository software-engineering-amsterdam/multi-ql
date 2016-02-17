import { AnalyzingQlParser } from 'src/parser';
import { NodeNormalizer } from 'src/ast_normalization';
import { SemanticAnalyser } from 'src/ast_semantic_analysis';
import { WidgetFactory } from 'src/widgets';
import { ExprObservableFactory } from 'src/expr_observable';
import { Renderer } from 'src/rendering';
import * as ace from 'ace';

let LOCALSTORAGE_KEY = 'uva-software-process-ql-merijn-last-input',
	editor = ace.edit('input'),
	session = editor.getSession(),
	logElement = document.getElementById('log'),
	renderElement = document.getElementById('render'),
	parser = new AnalyzingQlParser(new SemanticAnalyser()),
	renderer = new Renderer(document, new WidgetFactory(document), new ExprObservableFactory());

session.on('change', function (e) {
	let val = session.getValue(),
		parseResult = parser.parse(val),
		errors = parseResult.errors;

	session.clearAnnotations();
	if (errors.length > 0) {
		logElement.value = errors.map((lineError) => {
			return "[line " + lineError.line + "] " + lineError.message;
		}).join("\n");
		session.setAnnotations(errors.map((lineError) => {
			return {
				'row': lineError.line-1,
				'text': lineError.message,
				'type': 'error'
			};
		}));
	} else {
		logElement.value = "Ok";
		while (renderElement.firstChild !== null) {     // TODO make this reusable
			renderElement.removeChild(renderElement.firstChild);
		}
		renderer.render(parseResult.ast, renderElement);
	}

	window.localStorage.setItem(LOCALSTORAGE_KEY, val);
});
session.setValue(window.localStorage.getItem(LOCALSTORAGE_KEY));
