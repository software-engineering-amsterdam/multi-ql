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
    
    func createQuestionView(expression: Question, viewWidget: ViewWidget) -> UIView
    
    func createWidgetView(expression: BooleanField) -> ViewWidget
    func createWidgetView(expression: StringField) -> ViewWidget
    func createWidgetView(expression: Expression) -> ViewWidget
}

class ConcreteFormViewFactory: FormViewFactory {
    let formLayout: FormLayout
    
    init(formLayout: FormLayout) {
        self.formLayout = formLayout
    }
    
    
    func createQuestionView(question: Question, viewWidget: ViewWidget) -> UIView {
        return QuestionView(layout: formLayout.questionLayout, question: question, widget: viewWidget)
    }
    
    func createWidgetView(expression: BooleanField) -> ViewWidget {
        return BooleanWidget(layout: formLayout.widgetLayout, delegate: nil, booleanField: expression)
    }
    
    func createWidgetView(expression: StringField) -> ViewWidget {
        return TextWidget(layout: formLayout.widgetLayout, delegate: nil, stringField: expression)
    }
    
    func createWidgetView(expression: Expression) -> ViewWidget {
        return StaticWidget(layout: formLayout.widgetLayout, delegate: nil, expression: expression)
    }
}

