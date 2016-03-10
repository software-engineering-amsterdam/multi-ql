//
//  QuestionView.swift
//  QLSwift
//
//  Created by Tom van Duist on 12/02/16.
//
//

import UIKit

class QuestionView: BaseView, WidgetDelegate, QLContextDelegate, QLTypeVisitor {
    typealias QLTypeVisitorParam    = (layout: Layout, delegate: WidgetDelegate)
    typealias QLTypeVisitorReturn   = ViewWidget
    
    private let heightConstraint: NSLayoutConstraint

    private let contentView = BaseView()
    private let widgetContainer = BaseView()
    private let question: Question
    private var widget: Widget?
    
    
    init(layout: Layout, question: Question) {
        self.heightConstraint = NSLayoutConstraint(
            item: contentView,
            attribute: .Height,
            relatedBy: .Equal,
            toItem: nil,
            attribute: .NotAnAttribute,
            multiplier: 1.0,
            constant: 0
        )
        self.question = question
        
        super.init(frame: CGRectZero)
        
        
        let widgetView = createWidgetView(layout, question: question)
        
        setupView(layout, question: question, widget: widgetView)
        
        question.context.subscribe(self)
        
        update()
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("Storyboards are not supported!")
    }
    
    func widgetChangedValue(widget: Widget, value: NSObject) {
        question.context.assign(question.identifier, value: value)
    }
    
    func update() {
        widget?.setValue(question.context.retrieve(question.identifier))
        
        self.heightConstraint.active = !question.enabled()
        
        UIView.animateWithDuration(kAnimationDuration) { [unowned self] in
            self.layoutIfNeeded()
        }
    }
}


// MARK: - QLContextDelegate conformance

extension QuestionView {
    
    func contextUpdated(context: QLContext) {
        update()
    }
}


// MARK: - QLTypeVisitor conformance

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


// MARK: - Private

extension QuestionView {
    
    private func createWidgetView(layout: Layout, question: Question) -> ViewWidget {
        let widgetView = question.type.accept(self, param: (layout, self))
        widgetView.enabled = !question.isComputed
        self.widget = widgetView
        
        return widgetView
    }
    
    private func setupView(layout: Layout, question: Question, widget: ViewWidget) {
        widgetContainer.addSubview(widget)
        widget.snp_makeConstraints { [unowned widgetContainer] (make) -> Void in
            make.edges.equalTo(widgetContainer)
        }
        
        contentView.addSubview(widgetContainer)
        widgetContainer.snp_makeConstraints { [unowned contentView] (make) -> Void in
            make.left.equalTo(contentView.snp_left)
            make.right.equalTo(contentView.snp_right)
            make.bottom.equalTo(contentView.snp_bottom)
        }
        
        
        let label = UILabel()
        label.text = question.question
        
        contentView.addSubview(label)
        label.snp_makeConstraints { [unowned contentView, widgetContainer] (make) -> Void in
            make.top.equalTo(contentView.snp_top).offset(layout.margin.top)
            make.left.equalTo(contentView.snp_left).offset(layout.margin.left)
            make.right.equalTo(contentView.snp_right).offset(layout.margin.right)
            make.bottom.equalTo(widgetContainer.snp_top).offset(layout.margin.bottom)
        }
        
        
        let middleSeperator = UIView()
        middleSeperator.backgroundColor = UIColor.lightGrayColor()
        middleSeperator.alpha = 0.3
        contentView.addSubview(middleSeperator)
        middleSeperator.snp_makeConstraints { [unowned self, contentView] make in
            make.left.equalTo(contentView.snp_left)
            make.right.equalTo(contentView.snp_right)
            make.bottom.equalTo(self.widgetContainer.snp_top)
            make.height.equalTo(1)
        }
        
        let bottomSeperator = UIView()
        bottomSeperator.backgroundColor = UIColor.lightGrayColor()
        contentView.addSubview(bottomSeperator)
        bottomSeperator.snp_makeConstraints { [unowned contentView] make in
            make.left.equalTo(contentView.snp_left)
            make.right.equalTo(contentView.snp_right)
            make.bottom.equalTo(contentView.snp_bottom)
            make.height.equalTo(1)
        }
        
        self.addSubview(contentView)
        contentView.snp_makeConstraints { [unowned self] make in
            make.edges.equalTo(self)
        }
    }
}