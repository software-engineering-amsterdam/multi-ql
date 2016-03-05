//
//  BooleanView.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import UIKit

//class BooleanWidget: ViewWidget {
//    let booleanField: BooleanField
//    let toggle: UISwitch
//    
//    init(layout: Layout, delegate: WidgetDelegate?, booleanField: BooleanField) {
//        self.booleanField = booleanField
//        
//        toggle = UISwitch()
//        
//        super.init(layout: layout, delegate: delegate)
//        
//        toggle.on = booleanField.value
//        toggle.addTarget(self, action: "valueChanged:", forControlEvents: .ValueChanged)
//    }
//
//    required init?(coder aDecoder: NSCoder) {
//        fatalError("StoryBoards are not supported!")
//    }
//    
//    override func setupView(layout: Layout) {
//        
//        if toggle.superview == nil {
//            self.addSubview(toggle)
//            
//            toggle.snp_makeConstraints { [unowned self] (make) -> Void in
//                make.top.equalTo(self.snp_top).offset(layout.margin.top)
//                make.right.equalTo(self.snp_right).offset(layout.margin.right)
//                make.bottom.equalTo(self.snp_bottom).offset(layout.margin.bottom)
//            }
//        }
//    }
//    
//    internal func valueChanged(sender: UISwitch) {
//        booleanField.value = sender.on
//        delegate?.widgetChangedValue(self, value: sender.on)
//    }
//}