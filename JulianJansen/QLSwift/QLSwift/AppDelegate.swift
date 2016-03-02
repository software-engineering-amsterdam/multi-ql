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
        
        testing()
    }

    func applicationWillTerminate(aNotification: NSNotification) {
        // Insert code here to tear down your application
    }

    func testing() {
        let stream = readFile("basic", fileType: "ql")
        
        do {
            let test = try QLParser().parseStream(stream!)
            print("After parsing")
            print(test.formName.string)
            print(test.codeBlock)
            
            var temp = test.codeBlock[0] as! QLQuestion
            print(temp.name)
            print(temp.variable)
            print(temp.type)


            
        } catch {
            print("Error in do-catch in AppDelegate: \(error)")
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

