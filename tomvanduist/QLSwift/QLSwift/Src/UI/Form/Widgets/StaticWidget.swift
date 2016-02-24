//
//  StaticWidget.swift
//  QLSwift
//
//  Created by Tom van Duist on 24/02/16.
//
//

import UIKit

class StaticWidget: ViewWidget {
    let expression: Expression
    var textField: UITextField?
    
    init(layout: Layout, delegate: WidgetDelegate?, expression: Expression) {
        self.expression = expression
        
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
            textField!.textColor = UIColor.lightGrayColor()
            
            updateValue()
            
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
    
    override func reloadView() {
        super.reloadView()
        
        updateValue()
    }
    
    private func updateValue() {
        if expression.type === BooleanType() {
            textField!.text = (expression.eval() as! Bool) ? "Yes" : "No"
        } else if let value = expression.eval() {
            textField!.text = "\(value)"
        } else {
            textField!.text = ""
        }
    }
}