//
//  Widget.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import UIKit

protocol Widget {
    var enabled: Bool { get set }
    var delegate: WidgetDelegate { get }
    
    func setValue(value: NSObject) -> Bool
}

protocol WidgetDelegate {
    func widgetChangedValue(widget: Widget, value: NSObject)
}

class ViewWidget: UIView, Widget {
    var enabled: Bool = false { didSet { enable(enabled) } }
    internal let delegate: WidgetDelegate
    
    init(layout: Layout, delegate: WidgetDelegate) {
        self.delegate = delegate
        
        super.init(frame: CGRectZero)
        
        setupView(layout)
        
        self.enabled = false
    }
    
    func enable(enable: Bool) {
        self.userInteractionEnabled = enable
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("StoryBoards are not supported!")
    }
    
    func setupView(layout: Layout) {
        fatalError("Override")
    }
    
    func setValue(value: NSObject) -> Bool {
        fatalError("Override")
    }
}

extension QLType {
    func widgetView(layout: Layout, delegate: WidgetDelegate) -> ViewWidget {
        fatalError("Override")
    }
}
