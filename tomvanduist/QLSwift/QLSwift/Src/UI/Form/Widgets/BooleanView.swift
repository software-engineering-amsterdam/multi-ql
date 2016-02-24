//
//  BooleanView.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import UIKit

class BooleanView: ViewWidget {
    
    init(delegate: WidgetDelegate) {
        super.init(delegate: delegate, nibName: "BooleanView")
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("Not supported")
    }
    
    @IBAction func valueChanged(sender: UISwitch) {
        self.delegate.valueChanged(sender.on)
    }
}