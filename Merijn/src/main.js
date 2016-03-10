import { AnalyzingQlParser } from 'src/ql/parser';
import { SemanticAnalyser } from 'src/ql/ast_semantic_analysis';
import { Parser as QlsParser } from 'src/qls/parser';
import { Log } from 'src/log';
import { Renderer as QlRenderer, WidgetFactory as QlWidgetFactory } from 'src/ql/rendering';
import { Renderer as QlsRenderer } from 'src/qls/rendering';
import * as ace from 'ace';

let LOCALSTORAGE_KEY = 'uva-software-process-ql-merijn-last-input',
	QLS_LOCALSTORAGE_KEY = 'uva-software-process-ql-merijn-last-input-qls',
	qlEditor = ace.edit('input-ql'),
	qlSession = qlEditor.getSession(),
	qlLogElement = document.getElementById('log-ql'),
	qlParser = new AnalyzingQlParser(new SemanticAnalyser()),
	qlsEditor = ace.edit('input-qls'),
	qlsSession = qlsEditor.getSession(),
	qlsLogElement = document.getElementById('log-qls'),
	qlsParser = new QlsParser(),
	renderElement = document.getElementById('render'),
	qlRenderer = new QlRenderer(document),
	qlsRenderer = new QlsRenderer(document, new QlWidgetFactory(document )),
	qlAst,
	qlsAst;

function addLogMessages(logMessages, type, outputMessages, annotations) {
	"use strict";

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

function render() {
	"use strict";

	while (renderElement.firstChild !== null) {
		renderElement.removeChild(renderElement.firstChild);
	}
	if (qlAst) {
		if (qlsAst) {
			qlsRenderer.render(qlAst, qlsAst, renderElement);
		} else {
			qlRenderer.render(qlAst, renderElement);
		}
	}
}

qlSession.on('change', function (e) {
	"use strict";

	let val = qlSession.getValue(),
		log = new Log(),
		outputMessages = [],
		annotations = [];

	window.localStorage.setItem(LOCALSTORAGE_KEY, val); // store before parse, as otherwise errors will block storing

	qlAst = qlParser.parse(val, log);
	addLogMessages(log.errors, 'error', outputMessages, annotations);
	addLogMessages(log.warnings, 'warning', outputMessages, annotations);
	qlLogElement.value = outputMessages.join("\n");
	qlSession.setAnnotations(annotations);
	if (!log.hasErrors()) {
		render();
	}
});
qlSession.setValue(window.localStorage.getItem(LOCALSTORAGE_KEY));

qlsSession.on('change', function (e) {
	"use strict";

	let val = qlsSession.getValue(),
		log = new Log(),
		outputMessages = [],
		annotations = [];

	window.localStorage.setItem(QLS_LOCALSTORAGE_KEY, val);

	qlsAst = qlsParser.parse(val, log);
	addLogMessages(log.errors, 'error', outputMessages, annotations);
	addLogMessages(log.warnings, 'warning', outputMessages, annotations);
	qlsLogElement.value = outputMessages.join("\n");
	qlsSession.setAnnotations(annotations);
	if (!log.hasErrors()) {
		render();
	}
});
qlsSession.setValue(window.localStorage.getItem(QLS_LOCALSTORAGE_KEY));