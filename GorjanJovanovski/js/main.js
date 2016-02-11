"use strict";

var antlr4 = require('antlr4/index');
var MyGrammerLexer = require('MyGrammerLexer');
var MyGrammerParser = require('MyGrammerParser');
var MyGrammerListener = require('MyGrammerListener');
var MyGrammerVisitor = require('MyGrammerVisitor');


var editor = ace.edit("input");

var ast;
var errors;
var warnings;

//Start
initiate(editor.getValue());

editor.commands.addCommand({
    name: 'regenerate',
    bindKey: {win: 'Ctrl-S',  mac: 'Command-S'},
    exec: function(editor) {
        initiate(editor.getValue());
    },
    readOnly: true // false if this command should not apply in readOnly mode
});


