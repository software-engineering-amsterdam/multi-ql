import { AnalyzingQlParser } from 'src/parser';
import { NodeNormalizer } from 'src/ast_normalization';
import { SemanticAnalyser } from 'src/ast_semantic_analysis';
import * as ace from 'ace';

let LOCALSTORAGE_KEY = 'uva-software-process-ql-merijn-last-input',
	editor = ace.edit('input'),
	session = editor.getSession(),
	logElement = document.getElementById('log'),
	renderElement = document.getElementById('render'),
	parser = new AnalyzingQlParser(new SemanticAnalyser());

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
	}

	window.localStorage.setItem(LOCALSTORAGE_KEY, val);
});
session.setValue(window.localStorage.getItem(LOCALSTORAGE_KEY));
