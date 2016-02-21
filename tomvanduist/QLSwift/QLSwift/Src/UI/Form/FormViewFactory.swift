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
    
    func createFormView(form: Form) -> UIView
    func createQuestionView(expression: Question) -> UIView
    func createWidgetView(expression: BooleanField) -> ViewWidget
}

class ConcreteFormViewFactory: FormViewFactory {
    let formLayout: FormLayout
    
    init(formLayout: FormLayout) {
        self.formLayout = formLayout
    }
    
    func createFormView(form: Form) -> UIView {
        let view = createBlockView(form.block)
        
//        for statement in form.block.block {
//            if let question = statement as? Question {
//                let questionView = createQuestionView(question)
//                
//                view.addSubview(questionView)
//                
//                questionView.snp_makeConstraints { (make) -> Void in
//                    make.edges.equalTo(view)
//                }
//                
//                break
//            }
//        }
        
        return view
    }
    
    
    func createQuestionView(question: Question) -> UIView {
        
        if let booleanField = question.expression as? BooleanField {
            
            let widget = createWidgetView(booleanField)
            
            let questionView = QuestionView(layout: formLayout.questionLayout, question: question.label, widget: widget)
            
            return questionView
        }
        
        return UIView()
    }
    
    
    func createWidgetView(expression: BooleanField) -> ViewWidget {
        let widgetView = BooleanWidget(layout: formLayout.widgetLayout)
        return widgetView
    }
}


// MARK: Private

extension FormViewFactory {
    
    private func createBlockView(block: Block) -> UIView {
        let view = UIView()
        
        if let s = block.block.first {
            var prevView = placeStatement(s, superView: view, prevView: nil, nextView: block.block.count == 1 ? view : nil)
            
            if block.block.count > 1 {
                for i in 1...(block.block.count-1) {
                    prevView = placeStatement(block.block[i], superView: view, prevView: prevView, nextView: block.block.count == i+1 ? view : nil)
                }
            }
        }
        
        return view
    }
    
    private func placeStatement(statement: Statement, superView: UIView, prevView: UIView?, nextView: UIView?) -> UIView {
        let view = createStatementView(statement)
        
        superView.addSubview(view)
        
        view.snp_updateConstraints { [unowned superView, prevView] (make) -> Void in
            make.left.equalTo(superView.snp_left)
            make.right.equalTo(superView.snp_right)
            if let prevView = prevView {
                make.top.equalTo(prevView.snp_bottom)
            } else {
                make.top.equalTo(superView.snp_top)
            }
            if let nextView = nextView {
                make.bottom.equalTo(nextView.snp_bottom)
            }
        }
        
        return view
    }
    
    private func createStatementView(statement: Statement) -> UIView {
        if let question = statement as? Question {
            return createQuestionView(question)
        } else if let conditional = statement as? Conditional {
            return createConditionalView(conditional)
        } else if let block = statement as? Block {
            return createBlockView(block)
        }
        fatalError("Implement for remaining statements")
    }
    
    private func createConditionalView(conditional: Conditional) -> UIView {
        return createBlockView(conditional.ifBlock)
    }
}