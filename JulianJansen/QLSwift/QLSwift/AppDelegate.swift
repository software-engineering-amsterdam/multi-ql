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
        
        runTestBed()
    }

    func applicationWillTerminate(aNotification: NSNotification) {
        // Insert code here to tear down your application
    }

    func testQLParser() {
        let stream = readFile("basic", fileType: "ql")
        
        do {
            let test = try QLParser().parseStream(stream!)
            print("After parsing")
            print(test.formName)
            print(test.codeBlock)
            
            var temp = test.codeBlock[0] as! QLQuestion
            print(temp.name)
            print(temp.variable)
            print(temp.type)
            
            temp = test.codeBlock[1] as! QLQuestion
            print(temp.name)
            print(temp.variable)
            print(temp.type)
            
            
            temp = test.codeBlock[2] as! QLQuestion
            print(temp.name)
            print(temp.variable)
            print(temp.type)


            
        } catch {
            print("Error in do-catch of testQLParser in AppDelegate: \(error)")
        }
    }
    
    func runTestBed() {
        let stream = readFile("expression", fileType: "ql")
        
        do {
            let test = try TestBed().parseStream(stream!)
            print("After parsing")
            print(test)
            
        } catch {
            print("Error in do-catch of runTestBed() in AppDelegate: \(error)")
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

