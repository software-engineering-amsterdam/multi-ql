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
    let questionLayout: Layout = Layout(margin: UIEdgeInsets(top: 20, left: 20, bottom: -10, right: -20))
    let widgetLayout: Layout = Layout(margin: UIEdgeInsets(top: 10, left: 20, bottom: -20, right: -20))
}