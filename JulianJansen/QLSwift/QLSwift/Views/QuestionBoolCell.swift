//
//  QuestionBoolCell.swift
//  QLSwift
//
//  Created by Julian Jansen on 05-04-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Cocoa

class QuestionBoolCell: NSView {

    @IBOutlet weak var label: NSTextField!
    @IBOutlet weak var boolSwitch: NSButton!
    
    var name = String()
    
    @IBAction func stateDidChange(sender: AnyObject) {
        
        var boolValue = Bool()
        
        // Convert to Int (Objective-C/NS legacy).
        if (boolSwitch.state == 1) {
            boolValue = true
        } else {
            boolValue = false
        }
        
        NSNotificationCenter.defaultCenter().postNotificationName("valueChanged", object: nil, userInfo: ["name": name, "value": QLBoolLiteral(boolean: boolValue)])
    }
    
}
