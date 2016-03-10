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
            textField.placeholder = ""
            textField.addTarget(self, action: "textFieldDidChange:", forControlEvents: UIControlEvents.EditingChanged)
            
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
    
    override internal func enable(enable: Bool) {
        super.enable(enable)
        
        textField.backgroundColor = enable ? UIColor.whiteColor() : UIColor.lightGrayColor()
    }
    
    override func setValue(value: NSObject) -> Bool {
        guard let integerValue = value as? NSInteger
            else { return false }
        
        textField.text = "\(integerValue)"
        
        return true
    }
    
    func textField(textField: UITextField, shouldChangeCharactersInRange range: NSRange, replacementString string: String) -> Bool {
        let digits = NSCharacterSet.decimalDigitCharacterSet()
        
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
        var newValue: NSInteger!
        
        if value == nil || value!.characters.count == 0 {
            newValue = 0
        } else {
            newValue = NSInteger(value!)
        }
        
        delegate?.widgetChangedValue(self, value: newValue)
    }
}


extension QLIntegerType {
    func widgetView(layout: Layout, delegate: WidgetDelegate) -> ViewWidget {
        return IntegerWidget(layout: layout, delegate: delegate)
    }
}
