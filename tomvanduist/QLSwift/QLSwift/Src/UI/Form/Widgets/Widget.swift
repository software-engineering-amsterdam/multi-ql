//
//  Widget.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import UIKit

protocol Widget {
    var delegate: WidgetDelegate { get }
}

class ViewWidget: UIView, Widget {
    internal let delegate: WidgetDelegate
    
    init(delegate: WidgetDelegate, nibName: String) {
        self.delegate = delegate
        
        super.init(frame: CGRectZero)
        
        self.setViewWithNib(nibName, owner: self)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("Not supported")
    }
}

protocol WidgetDelegate {
    func valueChanged(value: NSValue)
}