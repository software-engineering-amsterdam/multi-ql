//
//  UITextField.swift
//  QLSwift
//
//  Created by Tom van Duist on 10/03/16.
//
//

import UIKit


extension UITextField {
    func textRange(range: NSRange) -> UITextRange? {
        let beginning = self.beginningOfDocument
        
        guard let start = self.positionFromPosition(beginning, inDirection: .Right, offset: range.location)
            else { return nil }
        
        guard let end = self.positionFromPosition(start, inDirection: .Right, offset: range.length)
            else { return nil }
        
        return self.textRangeFromPosition(start, toPosition: end)
    }
}