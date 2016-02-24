//
//  Widget.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import UIKit

protocol Widget {
    var delegate: WidgetDelegate? { get }
}

protocol WidgetDelegate {
    func widgetChangedValue(widget: Widget, value: NSValue)
}

class ViewWidget: UIView, Widget {
    internal var delegate: WidgetDelegate?
    
    convenience init(layout: Layout) {
        self.init(layout: layout, delegate: nil)
    }
    
    init(layout: Layout, delegate: WidgetDelegate?) {
        super.init(frame: CGRectZero)
        
        self.delegate = delegate
        
        setupView(layout)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("Not supported")
    }
    
    func setupView(layout: Layout) {
        fatalError("Override")
    }
}