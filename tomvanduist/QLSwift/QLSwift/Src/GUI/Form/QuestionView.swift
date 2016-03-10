//
//  QuestionView.swift
//  QLSwift
//
//  Created by Tom van Duist on 12/02/16.
//
//

import UIKit

class QuestionView: BaseView, ViewContainable, WidgetDelegate, QLTypeVisitor {
    typealias QLTypeVisitorParam    = (layout: Layout, delegate: WidgetDelegate)
    typealias QLTypeVisitorReturn   = ViewWidget
    
    internal let viewContainer = BaseView()
    private let context: QLContext
    private let identifier: String
    
    
    init(layout: Layout, question: QLVariableQuestion, context: QLContext) {
        self.context = context
        self.identifier = question.identifier.id
        
        super.init(frame: CGRectZero)
        
        let widget = question.type.accept(self, param: (layout, self))
        
        setupView(layout, question: question, widget: widget)
    }
    
    init(layout: Layout, question: QLComputedQuestion, type: QLType, context: QLContext) {
        self.context = context
        self.identifier = question.identifier.id
        
        super.init(frame: CGRectZero)
        
        let widget = type.accept(self, param: (layout, self))
        widget.enabled = false
        
        setupView(layout, question: question, widget: widget)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("Storyboards are not supported!")
    }
    
    func widgetChangedValue(widget: Widget, value: NSObject) {
        context.assign(identifier, value: value)
    }
    
    private func setupView(layout: Layout, question: QLQuestion, widget: ViewWidget) {
        self.viewContainer.addSubview(widget)
        widget.snp_makeConstraints { [unowned viewContainer] (make) -> Void in
            make.edges.equalTo(viewContainer)
        }
        
        self.addSubview(viewContainer)
        viewContainer.snp_makeConstraints { [unowned self] (make) -> Void in
            make.left.equalTo(self.snp_left)
            make.right.equalTo(self.snp_right)
            make.bottom.equalTo(self.snp_bottom)
        }
        
        
        let label = UILabel()
        label.text = question.label
        
        self.addSubview(label)
        
        label.snp_makeConstraints { [unowned self, viewContainer] (make) -> Void in
            make.top.equalTo(self.snp_top).offset(layout.margin.top)
            make.left.equalTo(self.snp_left).offset(layout.margin.left)
            make.right.equalTo(self.snp_right).offset(layout.margin.right)
            make.bottom.equalTo(viewContainer.snp_top).offset(layout.margin.bottom)
        }
        
        
        let middleSeperator = UIView()
        middleSeperator.backgroundColor = UIColor.lightGrayColor()
        middleSeperator.alpha = 0.3
        self.addSubview(middleSeperator)
        middleSeperator.snp_makeConstraints { [unowned self] make in
            make.left.equalTo(self.snp_left)
            make.right.equalTo(self.snp_right)
            make.bottom.equalTo(self.viewContainer.snp_top)
            make.height.equalTo(1)
        }
        
        let bottomSeperator = UIView()
        bottomSeperator.backgroundColor = UIColor.lightGrayColor()
        self.addSubview(bottomSeperator)
        bottomSeperator.snp_makeConstraints { [unowned self] make in
            make.left.equalTo(self.snp_left)
            make.right.equalTo(self.snp_right)
            make.bottom.equalTo(self.snp_bottom)
            make.height.equalTo(1)
        }
    }
}


extension QuestionView {
    func visit(node: QLStringType, param: (layout: Layout, delegate: WidgetDelegate)) -> ViewWidget {
        return node.widgetView(param.layout, delegate: param.delegate)
    }
    
    func visit(node: QLBooleanType, param: (layout: Layout, delegate: WidgetDelegate)) -> ViewWidget {
        return node.widgetView(param.layout, delegate: param.delegate)
    }
    
    func visit(node: QLIntegerType, param: (layout: Layout, delegate: WidgetDelegate)) -> ViewWidget {
        return node.widgetView(param.layout, delegate: param.delegate)
    }
    
    func visit(node: QLUnknownType, param: (layout: Layout, delegate: WidgetDelegate)) -> ViewWidget {
        fatalError()
    }
    
    func visit(node: QLVoidType, param: (layout: Layout, delegate: WidgetDelegate)) -> ViewWidget {
        fatalError()
    }
}