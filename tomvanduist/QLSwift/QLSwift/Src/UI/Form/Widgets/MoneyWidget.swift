//
//  DecimalView.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import UIKit

class MoneyWidget: ViewWidget, UITextFieldDelegate {
    let moneyField: MoneyField
    let textField: UITextField
    
    init(layout: Layout, delegate: WidgetDelegate?, moneyField: MoneyField) {
        self.moneyField = moneyField
        
        textField = UITextField()
        
        super.init(layout: layout, delegate: delegate)
        
        textField.keyboardType = .DecimalPad
        textField.borderStyle = .Line
        textField.textAlignment = .Right
        textField.delegate = self
        textField.placeholder = "0.00"
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("StoryBoards are not supported!")
    }
    
    override func setupView(layout: Layout) {
        
        if textField.superview == nil {
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
    
    func textFieldDidEndEditing(sender: UITextField) {
        if let text = sender.text, let value = Double(text) {
            moneyField.value = NSInteger(round(value * 100.0)) // Money represented as integer
        } else {
            moneyField.value = 0
        }
        
        delegate?.widgetChangedValue(self, value: moneyField.value)
    }
}