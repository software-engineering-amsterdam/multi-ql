import { AnalyzingQlParser } from 'src/parser';
import { NodeNormalizer } from 'src/ast_normalization';
import { SemanticAnalyser } from 'src/ast_semantic_analysis';
import { Log } from 'src/log';
import { WidgetFactory } from 'src/widgets';
import { ExprObservableFactory } from 'src/expr_observable';
import { Renderer } from 'src/rendering';
import * as ace from 'ace';

let LOCALSTORAGE_KEY = 'uva-software-process-ql-merijn-last-input',
	editor = ace.edit('input'),
	session = editor.getSession(),
	logElement = document.getElementById('log'),
	renderElement = document.getElementById('render'),
	parser = new AnalyzingQlParser(new SemanticAnalyser());
	//renderer = new Renderer(document, new WidgetFactory(document), new ExprObservableFactory());

session.on('change', function (e) {
	"use strict";

	let val = session.getValue(),
		log = new Log(),
		ast,
		messages = [],
		annotations = [];

	window.localStorage.setItem(LOCALSTORAGE_KEY, val); // store before parse, as otherwise errors will block storing

	ast = parser.parse(val, log);
	log.errors.forEach((error) => {
		messages.push("[ERROR] line(s): " + error.lines.join(',') + " - " + error.message);
		error.lines.forEach((line) => {
			annotations.push({
				'row': line-1,
				'text': error.message,
				'type': 'error'
			});
		});
	});
	log.warnings.forEach((warning) => {
		messages.push("[WARNING] line(s): " + warning.lines.join(',') + " - " + warning.message);
		warning.lines.forEach((line) => {
			annotations.push({
				'row': line-1,
				'text': warning.message,
				'type': 'warning'
			});
		});
	});
	logElement.value = messages.join("\n");
	session.setAnnotations(annotations);
	if (!log.hasErrors()) {
		// TODO
		//while (renderElement.firstChild !== null) {     // TODO make this reusable
		//	renderElement.removeChild(renderElement.firstChild);
		//}
		//renderer.render(ast, renderElement);
	}
});
session.setValue(window.localStorage.getItem(LOCALSTORAGE_KEY));
