//
//  BooleanView.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import UIKit

class BooleanWidget: ViewWidget {
    let toggle: UISwitch = UISwitch()
    
    override func setupView(layout: Layout) {
        if toggle.superview == nil {
            toggle.on = false
            toggle.addTarget(self, action: "valueChanged:", forControlEvents: .ValueChanged)
            
            self.addSubview(toggle)
            
            toggle.snp_makeConstraints { [unowned self] (make) -> Void in
                make.top.equalTo(self.snp_top).offset(layout.margin.top)
                make.right.equalTo(self.snp_right).offset(layout.margin.right)
                make.bottom.equalTo(self.snp_bottom).offset(layout.margin.bottom)
            }
        }
    }
    
    override internal func enable(enable: Bool) {
        super.enable(enable)
        
        toggle.alpha = enable ? 1.0 : 0.3
    }
    
    override func setValue(value: NSObject) -> Bool {
        guard let boolValue = value as? Bool
            else { return false }
        
        toggle.on = boolValue
        
        return true
    }
    
    internal func valueChanged(sender: UISwitch) {
        delegate?.widgetChangedValue(self, value: sender.on)
    }
}


extension QLBooleanType {
    func widgetView(layout: Layout, delegate: WidgetDelegate) -> ViewWidget {
        return BooleanWidget(layout: layout, delegate: delegate)
    }
}
