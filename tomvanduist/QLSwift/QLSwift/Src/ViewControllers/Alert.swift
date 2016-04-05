//
//  Alert.swift
//  QLSwift
//
//  Created by Tom van Duist on 24/03/16.
//
//

import Foundation


class Alert: NSObject {
    let title:          String
    let message:        String
    let cancelButton:   String?
    let cancelBlock:    (() -> Void)?
    let confirmButton:  String
    let confirmBlock:   (() -> Void)?
    
    init(title: String, message: String, cancelButton: String?, confirmButton: String, cancelBlock: (() -> Void)? = nil, confirmBlock: (() -> Void)? = nil) {
        self.title = title
        self.message = message
        self.cancelButton = cancelButton
        self.cancelBlock = cancelBlock
        self.confirmButton = confirmButton
        self.confirmBlock = confirmBlock
    }
}