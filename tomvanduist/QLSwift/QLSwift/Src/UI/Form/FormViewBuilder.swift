//
//  FormViewBuilder.swift
//  QLSwift
//
//  Created by Tom van Duist on 24/02/16.
//
//

import UIKit


protocol FormViewBuilder {
    func buildFormView(form: Form) -> UIView
}

class ConcreteFormViewBuilder: FormViewBuilder {
    private let viewFactory: FormViewFactory
    
    init(formViewFactory: FormViewFactory) {
        self.viewFactory = formViewFactory
    }
    
    func buildFormView(form: Form) -> UIView {
        let formView = FormView()
        let blockView = createBlockView(form.block, formView: formView)
        
        formView.addSubview(blockView)
        blockView.snp_makeConstraints { [unowned formView] (make) -> Void in
            make.edges.equalTo(formView)
        }
        
        return formView
    }
}


extension ConcreteFormViewBuilder {
    private func createBlockView(block: Block, formView: FormView) -> UIView {
        let view = UIView()
        
        if let s = block.block.first {
            var prevView = placeStatement(s, formView: formView, superView: view, prevView: nil, nextView: block.block.count == 1 ? view : nil)
            
            if block.block.count > 1 {
                for i in 1...(block.block.count-1) {
                    prevView = placeStatement(block.block[i], formView: formView, superView: view, prevView: prevView, nextView: block.block.count == i+1 ? view : nil)
                }
            }
        }
        
        return view
    }
    
    private func placeStatement(statement: Statement, formView: FormView, superView: UIView, prevView: UIView?, nextView: UIView?) -> UIView {
        let view = createStatementView(statement, formView: formView)
        
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
    
    private func createStatementView(statement: Statement, formView: FormView) -> UIView {
        if let question = statement as? Question {
            return createQuestionView(question, formView: formView)
        } else if let conditional = statement as? Conditional {
            return createConditionalView(conditional, formView: formView)
        } else if let block = statement as? Block {
            return createBlockView(block, formView: formView)
        }
        fatalError("Implement for remaining statements")
    }
    
    private func createQuestionView(question: Question, formView: FormView) -> UIView {
        return self.viewFactory.createQuestionView(question, delegate: formView)
    }
    
    private func createConditionalView(conditional: Conditional, formView: FormView) -> UIView {
        return createBlockView(conditional.ifBlock, formView: formView)
    }
}