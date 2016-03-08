//
//  FormViewBuilder.swift
//  QLSwift
//
//  Created by Tom van Duist on 24/02/16.
//
//

import UIKit


//protocol FormViewBuilder {
//    func buildFormView(form: QLForm) -> UIView
//}
//
//class DefaultFormViewBuilder: QLNodeVisitor<FormView, UIView>, FormViewBuilder {
//    typealias GenericParam = FormView // TODO: refactor this !!
//    typealias GenericReturn = UIView
//    
//    
//    private let viewFactory: FormViewFactory
//    
//    
//    // MARK: - Initialization
//    
//    init(formViewFactory: FormViewFactory) {
//        self.viewFactory = formViewFactory
//    }
//    
//    
//    // MARK: - FormViewBuilder conformance
//    
//    func buildFormView(form: QLForm) -> UIView {
//        let formView = FormView()
//        let blockView = createBlockView(form.block, formView: formView)
//        
//        formView.addSubview(blockView)
//        blockView.snp_makeConstraints { [unowned formView] (make) -> Void in
//            make.edges.equalTo(formView)
//        }
//        
//        return formView
//    }
//
//    
//    // MARK: - Private 
//    
//    private func createBlockView(block: QLBlock, formView: FormView) -> UIView {
//        let blockView = UIView()
//        
//        if let s = block.block.first {
//            var prevView = placeStatement(s, formView: formView, superView: blockView, prevView: nil, nextView: block.block.count == 1 ? blockView : nil)
//            
//            if block.block.count > 1 {
//                for i in 1...(block.block.count-1) {
//                    prevView = placeStatement(block.block[i], formView: formView, superView: blockView, prevView: prevView, nextView: block.block.count == i+1 ? blockView : nil)
//                }
//            }
//        }
//        
//        return blockView
//    }
//    
//    private func placeStatement(statement: QLStatement, formView: FormView, superView: UIView, prevView: UIView?, nextView: UIView?) -> UIView {
//        let statementView = createStatementView(statement, formView: formView)
//        
//        superView.addSubview(statementView)
//        
//        statementView.snp_updateConstraints { [unowned superView, prevView] (make) -> Void in
//            make.left.equalTo(superView.snp_left)
//            make.right.equalTo(superView.snp_right)
//            if let prevView = prevView {
//                make.top.equalTo(prevView.snp_bottom)
//            } else {
//                make.top.equalTo(superView.snp_top)
//            }
//            if let nextView = nextView {
//                make.bottom.equalTo(nextView.snp_bottom)
//            }
//        }
//        
//        return statementView
//    }
//    
//    private func createStatementView(statement: QLStatement, formView: FormView) -> UIView {
//        return statement.accept(self, param: formView)
//    }
//    
//    private func createConditionalView(conditional: QLConditional, formView: FormView) -> UIView {
//        let conditionalView = ConditionalView(conditional: conditional)
//        let blockView = createBlockView(conditional.ifBlock, formView: formView)
//        
//        conditionalView.viewContainer.addSubview(blockView)
//        blockView.snp_makeConstraints { [unowned conditionalView] (make) -> Void in
//            make.edges.equalTo(conditionalView)
//        }
//        
//        return conditionalView
//    }
//    
//    private func createQuestionView(question: QLComputedQuestion, formView: FormView) -> UIView {
//        return self.viewFactory.createQuestionView(question, delegate: formView)
//    }
//    
//    
//    // MARK: - Visitor functions
//    
//    override func visit(node: QLComputedQuestion, param delegate: GenericParam) -> GenericReturn {
//        return self.createQuestionView(node, formView: delegate)
//    }
//    
//    override func visit(node: QLVariableQuestion, param delegate: GenericParam) -> GenericReturn {
//        //return self.createQuestionView(node, formView: delegate)
//        return UIView() // TODO: return questions
//    }
//    
//    override func visit(node: QLConditional, param delegate: GenericParam) -> GenericReturn {
//        return self.createConditionalView(node, formView: delegate) // TODO: fix this
//    }
//    
//    override func visit(node: QLBlock, param delegate: GenericParam) -> GenericReturn {
//        return self.createBlockView(node, formView: delegate)
//    }
//}