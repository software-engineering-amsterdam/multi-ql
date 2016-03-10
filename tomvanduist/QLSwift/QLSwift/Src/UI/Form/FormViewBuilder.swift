//
//  FormViewBuilder.swift
//  QLSwift
//
//  Created by Tom van Duist on 24/02/16.
//
//

import UIKit


protocol FormViewBuilder {
    func buildFormView(form: QLForm, context: QLContext) -> UIView
}

class DefaultFormViewBuilder: QLStatementVisitor, FormViewBuilder {
    typealias QLStatementVisitorParam   = QLContext
    typealias QLStatementVisitorReturn  = UIView
    
    var form:QLForm!
    
    func typeFor(computedQuestion: QLComputedQuestion, form: QLForm) -> QLType {
        guard let type = try? TypeInferer().inferTypes(form).retrieveType(computedQuestion.identifier.id)
            else { return QLUnknownType() }
        
        return type!
    }
    
//    private let viewFactory: FormViewFactory
//    
//    
//    // MARK: - Initialization
//    
//    init(formViewFactory: FormViewFactory) {
//        self.viewFactory = formViewFactory
//    }
    
    
    // MARK: - FormViewBuilder conformance
    
    func buildFormView(form: QLForm, context: QLContext) -> UIView {
        self.form = form
        
        let formView = FormView()
        let blockView = createBlockView(form.block, context: context)
        
        formView.addSubview(blockView)
        blockView.snp_makeConstraints { [unowned formView] (make) -> Void in
            make.edges.equalTo(formView)
        }
        
        return formView
    }

    
    // MARK: - Private 
    
    private func createBlockView(block: QLBlock, context: QLContext) -> UIView {
        let blockView = UIView()
        
        if let s = block.block.first {
            var prevView = placeStatement(s, context: context, superView: blockView, prevView: nil, nextView: block.block.count == 1 ? blockView : nil)
            
            if block.block.count > 1 {
                for i in 1...(block.block.count-1) {
                    prevView = placeStatement(block.block[i], context: context, superView: blockView, prevView: prevView, nextView: block.block.count == i+1 ? blockView : nil)
                }
            }
        }
        
        return blockView
    }
    
    private func placeStatement(statement: QLStatement, context: QLContext, superView: UIView, prevView: UIView?, nextView: UIView?) -> UIView {
        let statementView = createStatementView(statement, context: context)
        
        superView.addSubview(statementView)
        
        statementView.snp_updateConstraints { [unowned superView, prevView] (make) -> Void in
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
        
        return statementView
    }
    
    private func createStatementView(statement: QLStatement, context: QLContext) -> UIView {
        return statement.accept(self, param: context)
    }
    
    private func createConditionalView(conditional: QLConditional, context: QLContext) -> UIView {
        let conditionalView = ConditionalView(conditional: conditional, context: context)
        let blockView = createBlockView(conditional.ifBlock, context: context)
        
        conditionalView.viewContainer.addSubview(blockView)
        blockView.snp_makeConstraints { [unowned conditionalView] (make) -> Void in
            make.edges.equalTo(conditionalView)
        }
        
        return conditionalView
    }
    
    private func createQuestionView(question: QLComputedQuestion, context: QLContext) -> UIView {
        return QuestionView(layout: DefaultFormLayout().questionLayout, question: question, type: typeFor(question, form: form), context: context)
    }
    
    private func createQuestionView(question: QLVariableQuestion, context: QLContext) -> UIView {
        return QuestionView(layout: DefaultFormLayout().questionLayout, question: question, context: context)
    }
    
    
    // MARK: - Visitor functions
    
    func visit(node: QLComputedQuestion, param context: QLContext) -> UIView {
        return self.createQuestionView(node, context: context)
    }
    
    func visit(node: QLVariableQuestion, param context: QLContext) -> UIView {
        return self.createQuestionView(node, context: context)
    }
    
    func visit(node: QLConditional, param context: QLContext) -> UIView {
        return self.createConditionalView(node, context: context) // TODO: fix this
    }
    
    func visit(node: QLBlock, param context: QLContext) -> UIView {
        return self.createBlockView(node, context: context)
    }
}