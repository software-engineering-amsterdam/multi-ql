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
        
        
        testQLParser()
        
    }

    func applicationWillTerminate(aNotification: NSNotification) {
        // Insert code here to tear down your application
    }

    func testQLParser() {
        let stream = readFile("questionsAndIfStatement", fileType: "ql")
        
        do {
            let form = try QLParser().parseStream(stream!)

            let symbolsVisitor = SymbolsVisitor()
            
            form.accept(symbolsVisitor)
            
            let symbolTable = symbolsVisitor.getSymbolTable()
            
            print(symbolTable.getSymbolTable().description)
            
            let typeChecker = QLTypeChecker(symbolTable: symbolTable)
            
            form.accept(typeChecker)
            
            print(typeChecker.getTypeStack())

            
        } catch {
            print("Error in do-catch of testQLParser in AppDelegate: \(error)")
        }
    }
    
    
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

