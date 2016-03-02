//
//  FormLayout.swift
//  QLSwift
//
//  Created by Tom van Duist on 21/02/16.
//
//

import UIKit

class Layout {
    let margin: UIEdgeInsets
    
    init(margin: UIEdgeInsets
        ) {
        self.margin = margin
    }
}

protocol FormLayout {
    var questionLayout: Layout { get }
    var widgetLayout: Layout { get }
}

class DefaultFormLayout: FormLayout {
    let questionLayout: Layout = Layout(margin: UIEdgeInsetsMake(20.0, y: 20.0))
    let widgetLayout: Layout = Layout(margin: UIEdgeInsetsMake(20.0, y: 20.0))
}