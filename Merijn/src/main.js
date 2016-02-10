import { QlParser } from 'src/parser';
import { NodeNormalizer } from 'src/ast_normalization';
import { SemanticAnalyser } from 'src/ast_semantic_analysis';
import * as ace from 'ace';

var LOCALSTORAGE_KEY = 'uva-software-process-ql-merijn-last-input';

let editor = ace.edit('input'),
	session = editor.getSession(),
	logElement = document.getElementById('log'),
	renderElement = document.getElementById('render');

var parser = new QlParser(new SemanticAnalyser());

session.on('change', function (e) {
	window.localStorage.setItem(LOCALSTORAGE_KEY, session.getValue());
});
session.setValue(window.localStorage.getItem(LOCALSTORAGE_KEY));

//var parser = new QlParser(new SemanticAnalyser());
//let ast = parser.parse(`
//	Form {
//		"foo" bar boolean;
//		"bar" baz string;
//		"baz" quux integer;
//		"quux" foo float;
//		"quux" foo money;
//		if (bar == false) {
//			"foo" bar integer;
//		} else {
//			"bar" baz integer;
//		}
//	}
//`);