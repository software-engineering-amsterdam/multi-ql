"use strict";

var antlr4 = require('antlr4/index');
var MyGrammerLexer = require('MyGrammerLexer');
var MyGrammerParser = require('MyGrammerParser');
var MyGrammerListener = require('MyGrammerListener');
var MyGrammerVisitor = require('MyGrammerVisitor');
var editor = ace.edit("input");

var ast;

initiate(editor.getValue());