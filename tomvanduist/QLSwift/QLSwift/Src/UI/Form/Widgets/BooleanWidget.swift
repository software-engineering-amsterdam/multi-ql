//
//  BooleanView.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import UIKit

class BooleanWidget: ViewWidget {
    
    override func setupView(layout: Layout) {
        self.backgroundColor = UIColor.greenColor()
        
        let toggle = UISwitch()
        toggle.backgroundColor = UIColor.blackColor()
        
        self.addSubview(toggle)
        
        
        toggle.snp_makeConstraints { [unowned self] (make) -> Void in
            make.top.equalTo(self.snp_top).offset(layout.margin.top)
            make.right.equalTo(self.snp_right).offset(-layout.margin.right)
            make.bottom.equalTo(self.snp_bottom).offset(-layout.margin.bottom)
        }
    }
}