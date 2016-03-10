//
//  TextView.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import UIKit

class TextWidget: ViewWidget, UITextFieldDelegate {
    private let textField: UITextField = UITextField()
    
    override func setupView(layout: Layout) {
        if textField.superview == nil {
            textField.borderStyle = .Line
            textField.delegate = self
            textField.text = ""
            
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
        guard let stringValue = value as? String
            else { return false }
        
        textField.text = stringValue
        
        return true
    }
    
    func textFieldDidEndEditing(sender: UITextField) {
        delegate.widgetChangedValue(self, value: (sender.text != nil) ? sender.text! : "")
    }
}


extension QLStringType {
    func widgetView(layout: Layout, delegate: WidgetDelegate) -> ViewWidget {
        return TextWidget(layout: layout, delegate: delegate)
    }
}
