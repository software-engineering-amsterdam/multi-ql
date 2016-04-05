//
//  QuestionView.swift
//  QLSwift
//
//  Created by Tom van Duist on 12/02/16.
//
//

import UIKit

class QuestionView: BaseView, WidgetDelegate, ContextDelegate, TopDownType {
    private let heightConstraint: NSLayoutConstraint

    private let contentView = BaseView()
    private let widgetContainer = BaseView()
    private let question: Question
    private var widget: Widget?
    
    
    init(question: Question) {
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
        
        
        let widgetView = createWidgetView(question)
        
        setupView(question, widget: widgetView)
        
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
        if let value = question.eval() {
            widget?.setValue(value)
        }
        
        updateVisibility()
    }
    
    func updateVisibility() {
        self.heightConstraint.active = !question.enabled()
        
        UIView.animateWithDuration(kAnimationDuration) { [unowned self] in
            self.layoutIfNeeded()
        }
    }
}


// MARK: - QLContextDelegate

extension QuestionView {
    
    func contextUpdated(context: Context) {
        update()
    }
}


// MARK: - QLTypeVisitor

extension QuestionView {
    
    func visit(node: QLFloatType, param delegate: WidgetDelegate) -> ViewWidget {
        return node.widgetView(delegate)
    }
    
    func visit(node: QLStringType, param delegate: WidgetDelegate) -> ViewWidget {
        return node.widgetView(delegate)
    }
    
    func visit(node: QLBooleanType, param delegate: WidgetDelegate) -> ViewWidget {
        return node.widgetView(delegate)
    }
    
    func visit(node: QLIntegerType, param delegate: WidgetDelegate) -> ViewWidget {
        return node.widgetView(delegate)
    }
    
    func visit(node: QLUnknownType, param: WidgetDelegate) -> ViewWidget {
        fatalError()
    }
    
    func visit(node: QLVoidType, param: WidgetDelegate) -> ViewWidget {
        fatalError()
    }
    
    func defaultLeafResult(type: QLType, param: WidgetDelegate) -> ViewWidget {
        fatalError()
    }
}


// MARK: - Private

extension QuestionView {
    
    private func createWidgetView(question: Question) -> ViewWidget {
        let widgetView = question.type.accept(self, param: self)
        widgetView.enabled = !question.isComputed
        self.widget = widgetView
        
        return widgetView
    }
    
    private func setupView(question: Question, widget: ViewWidget) {
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
        
        
        let textView = UITextView()
        textView.text = question.question
        textView.scrollEnabled = false
        textView.font = UIFont.systemFontOfSize(18)
        textView.dataDetectorTypes = .All
        textView.editable = false
        
        contentView.addSubview(textView)
        textView.snp_makeConstraints { [unowned contentView, widgetContainer] (make) -> Void in
            make.top.equalTo(contentView.snp_top).offset(20)
            make.left.equalTo(contentView.snp_left).offset(20)
            make.right.equalTo(contentView.snp_right).offset(-20)
            make.bottom.equalTo(widgetContainer.snp_top).offset(-10)
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