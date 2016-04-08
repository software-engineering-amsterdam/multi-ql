//
//  ViewController.swift
//  QLSwift
//
//  Created by Julian Jansen on 04-04-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Cocoa

class ViewController: NSViewController {
        
    @IBOutlet weak var formName: NSTextField!
    @IBOutlet weak var questionsTableView: NSTableView!
    
    private let stream = Stream()
    private let form = Form()
    private var ast: QLForm?
    
    override func viewDidLoad() {
        
        NSNotificationCenter.defaultCenter().addObserver(self, selector: #selector(ViewController.updateValueInEnvironment), name: "valueChanged", object: nil)

        do {
            try stream.readFile(openFile())

            try ast = stream.parseStream()
            try runTypeChecker(ast)
            
            setupTableViewCells()

            try runFormBuilder(ast)
            
            formName.stringValue = form.getName()
            form.evaluateExpressions()
            
            questionsTableView.reloadData()
        } catch {
            AppLogger.sharedInstance.logError(error)
        }
        
        super.viewDidLoad()
        
        if (AppLogger.sharedInstance.didLog()) {
            presentErrorsAndWarnings()
        }
    }
    
    private func openFile() -> String? {
        let openFileDialog: NSOpenPanel = NSOpenPanel()
        openFileDialog.runModal()
        return openFileDialog.URL?.path
    }
    
    private func runTypeChecker(ast: QLForm?) throws {
        let typeChecker = QLTypeChecker()
        
        if (ast != nil) {
            ast!.accept(typeChecker)
        } else {
            throw ASTError(message: "Cannot run type checker. AST is empty.")
        }
        
        if (AppLogger.sharedInstance.getErrorCount() != 0) {
            throw TypeError(message: "Errors detected. Cannot execute.")
        }
    }
    
    private func runFormBuilder(ast: QLForm?) throws {
        let formBuilder = QLFormBuilder(form: form)
        
        if (ast != nil) {
            ast!.accept(formBuilder)
        } else {
            throw ASTError(message: "Cannot run form builder. AST is empty.")
        }
    }
    
    func updateValueInEnvironment(notification: NSNotification) {
        
        let name: String = notification.userInfo!["name"] as! String
        let value: QLLiteral = notification.userInfo!["value"] as! QLLiteral
        
        form.updateValue(name, value: value)
        
        do {
            form.clearQuestions()
            try runFormBuilder(ast)
            questionsTableView.reloadData()
        } catch {
            AppLogger.sharedInstance.logError(error)
        }
    }
    
    private func presentErrorsAndWarnings() {
        let alert = NSAlert()
        alert.messageText = "Warnings and Errors"
        alert.informativeText = AppLogger.sharedInstance.getReport()
        alert.addButtonWithTitle("Ok")
        alert.alertStyle = NSAlertStyle.CriticalAlertStyle
        alert.runModal()
    }
}

extension ViewController: NSTableViewDelegate {
    func numberOfRowsInTableView(tableView: NSTableView) -> Int {
        return form.getQuestionsCount()
    }
    
    func tableView(tableView: NSTableView, heightOfRow row: Int) -> CGFloat {
        return 50
    }
}

extension ViewController: NSTableViewDataSource {
    
    func tableView(tableView: NSTableView, viewForTableColumn tableColumn: NSTableColumn?, row: Int) -> NSView? {
        
        let questions = form.getQuestions()
        return makeQuestionCell(questions[row])
    }
    
    private func setupTableViewCells() {
        let boolCell = NSNib(nibNamed: "QuestionBoolCell", bundle: NSBundle.mainBundle())
        questionsTableView.registerNib(boolCell!, forIdentifier: "QuestionBoolCell")
        
        let stringCell = NSNib(nibNamed: "QuestionStringCell", bundle: NSBundle.mainBundle())
        questionsTableView.registerNib(stringCell!, forIdentifier: "QuestionStringCell")
        
        let intCell = NSNib(nibNamed: "QuestionIntCell", bundle: NSBundle.mainBundle())
        questionsTableView.registerNib(intCell!, forIdentifier: "QuestionIntCell")
    }
    
    private func makeQuestionCell(question: QLQuestion) -> NSView? {
        switch question.type {
        case QLBoolType():
            return makeBoolQuestionCell(question)
        case QLStringType():
            return makeStringQuestionCell(question)
        case QLIntegerType():
            return makeIntegerQuestionCell(question)
        default:
            return nil
        }
    }
    
    private func makeBoolQuestionCell(question: QLQuestion) -> NSView {
        let literal = form.getValue(question.name) as! QLBoolLiteral
        
        let cell = questionsTableView.makeViewWithIdentifier("QuestionBoolCell", owner: self) as! QuestionBoolCell
        cell.label.stringValue = question.label
        cell.name = question.name
        
        // Convert to Int (Objective-C/NS legacy).
        if (literal.value) {
            cell.boolSwitch.state = 1
        } else {
            cell.boolSwitch.state = 0
        }
        
        return cell
    }
    
    private func makeStringQuestionCell(question: QLQuestion) -> NSView {
        let literal = form.getValue(question.name) as! QLStringLiteral

        let cell = questionsTableView.makeViewWithIdentifier("QuestionStringCell", owner: self) as! QuestionStringCell
        cell.label.stringValue = question.label
        cell.input.stringValue = literal.value
        cell.name = question.name
        
        return cell
    }
    
    private func makeIntegerQuestionCell(question: QLQuestion) -> NSView {
        let literal = form.getValue(question.name) as! QLIntegerLiteral

        let cell = questionsTableView.makeViewWithIdentifier("QuestionIntCell", owner: self) as! QuestionIntCell
        cell.label.stringValue = question.label
        cell.input.stringValue = String(literal.value)
        cell.name = question.name
        
        return cell
    }
}