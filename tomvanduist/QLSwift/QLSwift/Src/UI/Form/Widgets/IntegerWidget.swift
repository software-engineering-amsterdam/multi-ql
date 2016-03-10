//
//  IntegerView.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import UIKit

class IntegerWidget: ViewWidget, UITextFieldDelegate {
    let textField: UITextField = UITextField()
    
    override func setupView(layout: Layout) {
        if textField.superview == nil {
            textField.keyboardType = .DecimalPad
            textField.borderStyle = .Line
            textField.textAlignment = .Right
            textField.delegate = self
            textField.placeholder = "0.00"
            
            self.addSubview(textField)
            
            textField.snp_makeConstraints { [unowned self] (make) -> Void in
                make.top.equalTo(self.snp_top).offset(layout.margin.top)
                make.left.equalTo(self.snp_left).offset(layout.margin.left)
                make.right.equalTo(self.snp_right).offset(layout.margin.right)
                make.bottom.equalTo(self.snp_bottom).offset(layout.margin.bottom)
                make.height.equalTo(50)
            }
        }
    }
    
    override func setValue(value: NSObject) -> Bool {
        guard let integerValue = value as? NSInteger
            else { return false }
        
        textField.text = "\(integerValue)"
        
        return true
    }
    
    func textField(textField: UITextField, shouldChangeCharactersInRange range: NSRange, replacementString string: String) -> Bool {
        let letters = NSCharacterSet.letterCharacterSet()
        
        for char in string.unicodeScalars {
            if letters.longCharacterIsMember(char.value) {
                return false
            }
        }
        
        return true
    }
    
    func textFieldDidEndEditing(sender: UITextField) {
        var newValue: NSInteger!
        
        if sender.text == nil || sender.text!.characters.count == 0 {
            newValue = 0
        } else {
            newValue = NSInteger(sender.text!)
        }
        
        delegate.widgetChangedValue(self, value: newValue)
    }
}


extension QLIntegerType {
    func widgetView(layout: Layout, delegate: WidgetDelegate) -> ViewWidget {
        return IntegerWidget(layout: layout, delegate: delegate)
    }
}
