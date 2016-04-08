//
//  QuestionIntCell.swift
//  QLSwift
//
//  Created by Julian Jansen on 05-04-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Cocoa

class QuestionIntCell: NSView, NSTextFieldDelegate {

    @IBOutlet weak var label: NSTextField!
    @IBOutlet weak var input: NSTextField!
    
    var name = String()
    
    override func controlTextDidEndEditing(obj: NSNotification) {
        NSNotificationCenter.defaultCenter().postNotificationName("valueChanged", object: nil, userInfo: ["name": name, "value": QLIntegerLiteral(integer: Int(input.intValue))])
    }
}
