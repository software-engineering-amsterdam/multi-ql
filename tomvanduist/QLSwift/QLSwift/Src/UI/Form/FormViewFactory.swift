//
//  FormViewFactory.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import SnapKit
import Foundation


protocol FormViewFactory {
    var formLayout: FormLayout { get }
    
    func createQuestionView(expression: Question, delegate: WidgetDelegate) -> UIView
    
    func createWidgetView(expression: BooleanField) -> ViewWidget
    func createWidgetView(expression: StringField) -> ViewWidget
    func createWidgetView(expression: MoneyField) -> ViewWidget
    func createWidgetView(expression: Expression) -> ViewWidget
}

class ConcreteFormViewFactory: DefaultASTNodeVisitor, FormViewFactory {
    typealias GenericReturn = UIView
    
    let formLayout: FormLayout
    
    init(formLayout: FormLayout) {
        self.formLayout = formLayout
    }
    
    
    func createQuestionView(question: Question, delegate: WidgetDelegate) -> UIView {
        let visitor = ViewFactoryNodeVisitor()
        let viewWidget = question.expression.accept(visitor, param: self)
        viewWidget.delegate = delegate
        
        return QuestionView(layout: formLayout.questionLayout, question: question, widget: viewWidget)
    }
    
    func createWidgetView(expression: BooleanField) -> ViewWidget {
        return BooleanWidget(layout: formLayout.widgetLayout, delegate: nil, booleanField: expression)
    }
    
    func createWidgetView(expression: StringField) -> ViewWidget {
        return TextWidget(layout: formLayout.widgetLayout, delegate: nil, stringField: expression)
    }
    
    func createWidgetView(expression: MoneyField) -> ViewWidget {
        return MoneyWidget(layout: formLayout.widgetLayout, delegate: nil, moneyField: expression)
    }
    
    func createWidgetView(expression: Expression) -> ViewWidget {
        return StaticWidget(layout: formLayout.widgetLayout, delegate: nil, expression: expression)
    }
}

class ViewFactoryNodeVisitor: ASTNodeVisitor<FormViewFactory, ViewWidget> {
    
    typealias GenericParam = FormViewFactory
    typealias GenericReturn = ViewWidget
    
    
    override func visit(node: StringField, param: GenericParam) -> GenericReturn {
        return param.createWidgetView(node)
    }
    
    override func visit(node: BooleanField, param: GenericParam) -> GenericReturn {
        return param.createWidgetView(node)
    }
    
    override func visit(node: MoneyField, param: GenericParam) -> GenericReturn {
        return param.createWidgetView(node)
    }
    
    override func visit(node: Identifier, param: GenericParam) -> GenericReturn {
        return param.createWidgetView(node)
    }
}
