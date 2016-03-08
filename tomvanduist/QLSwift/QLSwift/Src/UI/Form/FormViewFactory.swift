//
//  FormViewFactory.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import SnapKit
import Foundation


//protocol FormViewFactory {
//    var formLayout: FormLayout { get }
//    
//    func createQuestionView(expression: QLComputedQuestion, delegate: WidgetDelegate) -> UIView
//    
//    func createWidgetView(expression: BooleanField) -> ViewWidget
//    func createWidgetView(expression: StringField) -> ViewWidget
//    func createWidgetView(expression: MoneyField) -> ViewWidget
//    func createWidgetView(expression: QLExpression) -> ViewWidget
//}
//
//
//class DefaultFormViewFactory: QLNodeVisitor<FormViewFactory, ViewWidget>, FormViewFactory {
//    typealias GenericParam = FormViewFactory
//    typealias GenericReturn = ViewWidget
//    
//    let formLayout: FormLayout
//    
//    
//    // MARK: - Initialization
//    
//    init(formLayout: FormLayout) {
//        self.formLayout = formLayout
//    }
//    
//    
//    // MARK: - FormViewFactory conformance
//    
//    func createQuestionView(question: QLComputedQuestion, delegate: WidgetDelegate) -> UIView {
//        let viewWidget = question.expression.accept(self, param: self)
//        viewWidget.delegate = delegate
//        
//        return QuestionView(layout: formLayout.questionLayout, question: question, widget: viewWidget)
//    }
//    
//    func createWidgetView(expression: BooleanField) -> ViewWidget {
//        return BooleanWidget(layout: formLayout.widgetLayout, delegate: nil, booleanField: expression)
//    }
//    
//    func createWidgetView(expression: StringField) -> ViewWidget {
//        return TextWidget(layout: formLayout.widgetLayout, delegate: nil, stringField: expression)
//    }
//    
//    func createWidgetView(expression: MoneyField) -> ViewWidget {
//        return MoneyWidget(layout: formLayout.widgetLayout, delegate: nil, moneyField: expression)
//    }
//    
//    func createWidgetView(expression: QLExpression) -> ViewWidget {
//        return StaticWidget(layout: formLayout.widgetLayout, delegate: nil, expression: expression)
//    }
//
//    
//    // MARK: - Visitor functions
//
//    override func visit(node: StringField, param: GenericParam) -> GenericReturn {
//        return param.createWidgetView(node)
//    }
//    
//    override func visit(node: BooleanField, param: GenericParam) -> GenericReturn {
//        return param.createWidgetView(node)
//    }
//    
//    override func visit(node: MoneyField, param: GenericParam) -> GenericReturn {
//        return param.createWidgetView(node)
//    }
//    
//    override func visit(node: QLIdentifier, param: GenericParam) -> GenericReturn {
//        return param.createWidgetView(node)
//    }
//}
