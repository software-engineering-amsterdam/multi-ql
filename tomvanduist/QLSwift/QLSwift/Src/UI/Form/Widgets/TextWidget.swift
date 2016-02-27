//
//  TextView.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import UIKit

class TextWidget: ViewWidget, UITextFieldDelegate {
    let stringField: StringField
    var textField: UITextField?
    
    init(layout: Layout, delegate: WidgetDelegate?, stringField: StringField) {
        self.stringField = stringField
        
        super.init(layout: layout, delegate: delegate)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("Not supported")
    }
    
    override func setupView(layout: Layout) {
        self.backgroundColor = UIColor.greenColor()
        
        if textField == nil {
            textField = UITextField()
            textField!.borderStyle = .Line
            textField!.delegate = self
            textField!.text = ""
            
            self.addSubview(textField!)
            
            
            textField!.snp_makeConstraints { [unowned self] (make) -> Void in
                make.top.equalTo(self.snp_top).offset(layout.margin.top)
                make.left.equalTo(self.snp_left).offset(layout.margin.left)
                make.right.equalTo(self.snp_right).offset(-layout.margin.right)
                make.bottom.equalTo(self.snp_bottom).offset(-layout.margin.bottom)
                make.height.equalTo(50)
            }
        }
    }
    
    func textFieldDidEndEditing(sender: UITextField) {
        stringField.string = (sender.text != nil) ? sender.text! : ""
        delegate?.widgetChangedValue(self, value: stringField.string)
    }
}