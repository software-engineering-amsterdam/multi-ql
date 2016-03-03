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
		outputMessages = [],
		annotations = [];

	function addLogMessages(logMessages, type) {
		for (let logMessage of logMessages) {
			outputMessages.push("[" + type + "] line(s): " + logMessage.lines.join(',') + " - " + logMessage.message);
			for (let line of logMessage.lines) {
				annotations.push({
					'row': line-1,
					'text': logMessage.message,
					'type': type
				});
			}
		}
	}

	window.localStorage.setItem(LOCALSTORAGE_KEY, val); // store before parse, as otherwise errors will block storing

	ast = parser.parse(val, log);
	addLogMessages(log.errors, 'error');
	addLogMessages(log.warnings, 'warnings');
	logElement.value = outputMessages.join("\n");
	session.setAnnotations(annotations);
	if (!log.hasErrors()) {
		while (renderElement.firstChild !== null) {
			renderElement.removeChild(renderElement.firstChild);
		}
		//renderer.render(ast, renderElement);
	}
});
session.setValue(window.localStorage.getItem(LOCALSTORAGE_KEY));
