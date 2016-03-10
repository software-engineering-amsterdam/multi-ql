//
//  FormView.swift
//  QLSwift
//
//  Created by Tom van Duist on 24/02/16.
//
//

import UIKit

class FormView: UIView, WidgetDelegate {
    
    func widgetChangedValue(widget: Widget, value: NSObject) {
        self.reloadView()
    }
}
