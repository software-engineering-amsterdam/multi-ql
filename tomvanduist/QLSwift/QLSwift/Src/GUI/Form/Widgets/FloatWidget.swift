//
//  FloatWidget.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import UIKit

class FloatWidget: ViewWidget, UITextFieldDelegate {
    lazy var numberFormatter = NSNumberFormatter.localizedFloatingPointFormatter()
    
    let textField: UITextField = UITextField()
    
    override func setupView() {
        numberFormatter = NSNumberFormatter()
        if textField.superview == nil {
            textField.keyboardType = .DecimalPad
            textField.borderStyle = .Line
            textField.textAlignment = .Right
            textField.delegate = self
            textField.placeholder = ""
            textField.addTarget(self, action: "textFieldDidChange:", forControlEvents: UIControlEvents.EditingChanged)
            
            self.addSubview(textField)
            
            textField.snp_makeConstraints { [unowned self] (make) -> Void in
                make.edges.equalTo(self).offset(UIEdgeInsetsMake(20, 20, -10, -20))
                make.height.equalTo(50)
            }
        }
    }
    
    override internal func enable(enable: Bool) {
        super.enable(enable)
        
        textField.backgroundColor = enable ? UIColor.whiteColor() : UIColor.lightGrayColor()
    }
    
    override func setValue(value: NSObject) -> Bool {
        guard let floatValue = value as? Float
            else { return false }
        
        
        // Do not update when the values are the same, prevents removing of trailing .
        if let text = textField.text {
            if let number = numberFormatter.numberFromString(text) {
                if floatValue == number.floatValue {
                    return false
                }
            }
        }
        
        textField.text = numberFormatter.stringFromNumber(NSNumber(float: floatValue))
        
        return true
    }
    
    func textField(textField: UITextField, shouldChangeCharactersInRange range: NSRange, var replacementString string: String) -> Bool {
        let digits = NSCharacterSet.decimalDigitCharacterSet().mutableCopy()
        digits.addCharactersInString(".")
        
        for char in string.unicodeScalars {
            if !digits.longCharacterIsMember(char.value) {
                return false
            }
        }
        
        return true
    }
    
    func textFieldDidChange(textField: UITextField) {
        notifyDelegate(textField.text)
    }
    
    private func notifyDelegate(value: String?) {
        var newValue: Float?
        
        if value != nil {
            newValue = numberFormatter.numberFromString(value!)?.floatValue
        }
        
        delegate?.widgetChangedValue(self, value: newValue != nil ? newValue! : 0)
    }
}


extension QLFloatType {
    func widgetView(delegate: WidgetDelegate) -> ViewWidget {
        return FloatWidget(delegate: delegate)
    }
}
