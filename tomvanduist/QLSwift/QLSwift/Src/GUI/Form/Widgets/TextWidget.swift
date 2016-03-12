//
//  TextView.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import UIKit


let kTextPlaceholder = "Enter text"


class TextWidget: ViewWidget {
    private let textField: UITextField = UITextField()
    
    override func setupView() {
        if textField.superview == nil {
            textField.borderStyle = .Line
            textField.text = ""
            textField.placeholder = kTextPlaceholder
            textField.addTarget(self, action: "textFieldDidChange:", forControlEvents: UIControlEvents.EditingChanged)
            
            self.addSubview(textField)
            textField.snp_makeConstraints { [unowned self] (make) -> Void in
                make.edges.equalTo(self).offset(UIEdgeInsets(top: 20, left: 20, bottom: -10, right: -20))
                make.height.equalTo(50)
            }
        }
    }
    
    override internal func enable(enable: Bool) {
        super.enable(enable)
        
        textField.placeholder = enable ? kTextPlaceholder : ""
        textField.backgroundColor = enable ? UIColor.whiteColor() : UIColor.lightGrayColor()
    }
    
    override func setValue(value: NSObject) -> Bool {
        guard let stringValue = value as? String
            else { return false }
        
        textField.text = stringValue
        
        return true
    }
    
    func textFieldDidChange(textField: UITextField) {
        notifyDelegate(textField.text)
    }
    
    private func notifyDelegate(value: String?) {
        delegate?.widgetChangedValue(self, value: (value != nil) ? value! : "")
    }
}


extension QLStringType {
    func widgetView(delegate: WidgetDelegate) -> ViewWidget {
        return TextWidget(delegate: delegate)
    }
}
