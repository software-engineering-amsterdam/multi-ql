//
//  AppDelegate.swift
//  QLSwift
//
//  Created by Julian Jansen on 02-03-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Cocoa

@NSApplicationMain
class AppDelegate: NSObject, NSApplicationDelegate {

    @IBOutlet weak var window: NSWindow!


    func applicationDidFinishLaunching(aNotification: NSNotification) {
        // Insert code here to initialize your application
        
//        testQLParser()
        
//        runTestBed()
        
        testVisitorPattern()
        
    }

    func applicationWillTerminate(aNotification: NSNotification) {
        // Insert code here to tear down your application
    }

    func testQLParser() {
        let stream = readFile("questionsAndIfStatement", fileType: "ql")
        
        do {
            let form = try QLParser().parseStream(stream!)

            print(form.formName)

            for statement in form.codeBlock {
                print(statement)
            }
            
            let ifStatement = form.codeBlock[3] as! QLIfStatement
            
            let condition = ifStatement.condition as! QLGreaterOrIsExpression
            
            let rhs = (condition.rhs as! QLUnaryExpression).expression as! QLBool
            let lhs = (condition.lhs as! QLUnaryExpression).expression as! QLInteger
            print(rhs.boolean)
            print(lhs.integer)

            
       

            
        } catch {
            print("Error in do-catch of testQLParser in AppDelegate: \(error)")
        }
    }
    
//    func runTestBed() {
//        let stream = readFile("expression", fileType: "ql")
//        
//        do {
//            let test = try TestBed().parseStream(stream!) as! QLAndExpression
//            print("After parsing")
//            print(test)
//            print(((test.lhs as! QLUnaryExpression).expression as! QLBool).boolean)
//            print(((test.rhs as! QLUnaryExpression).expression as! QLBool).boolean)
//
//            
//        } catch {
//            print("Error in do-catch of runTestBed() in AppDelegate: \(error)")
//        }
//    }
    
    /// Returns an optional.
    private func readFile(fileName: String, fileType: String) -> String? {
        var text: String?
        
        if let path: String = NSBundle.mainBundle().pathForResource(fileName, ofType: fileType)! {
            do {
                text = try String(contentsOfFile: path, encoding: NSUTF8StringEncoding)
            } catch {
                /* error handling here */
            }
        }
        
        return text
    }

}

