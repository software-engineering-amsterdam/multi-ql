//
//  AppDelegate.swift
//  QL
//
//  Created by Jordy Heemskerk on 03/02/16.
//  Copyright © 2016 Jordy Heemskerk. All rights reserved.
//

import Cocoa
import SwiftParsec

@NSApplicationMain
class AppDelegate: NSObject, NSApplicationDelegate {

    @IBOutlet weak var window: NSWindow!


    func applicationDidFinishLaunching(aNotification: NSNotification) {
        guard let location = NSBundle.mainBundle().pathForResource("input", ofType: "ql") else {
            NSLog("File not found")
            exit(2)
        }
        let qlInput = try! String(contentsOfFile: location)
        
        let ql = QLParser.parse(qlInput)
        guard let questionair = ql else {
            print("Parse error")
            return
        }
        
        var (symbolTable, warnings, errors) = DeclarationChecker.check(questionair)
        (symbolTable, warnings, errors) = TypeChecker.check(questionair, withSymbolTable: symbolTable, warnings: warnings, andErrors: errors)
        
        guard errors.count == 0 else {
            errors.forEach {
                print("Error: \($0)")
            }
            return
        }
        
    }
    
    func applicationWillTerminate(aNotification: NSNotification) {
        // Insert code here to tear down your application
    }


}

