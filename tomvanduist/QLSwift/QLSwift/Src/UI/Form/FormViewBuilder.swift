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

class DefaultFormViewBuilder: ASTNodeVisitor<FormView, UIView>, FormViewBuilder {
    typealias GenericParam = FormView // TODO: refactor this !!
    typealias GenericReturn = UIView
    
    
    private let viewFactory: FormViewFactory
    
    
    // MARK: - Initialization
    
    init(formViewFactory: FormViewFactory) {
        self.viewFactory = formViewFactory
    }
    
    
    // MARK: - FormViewBuilder conformance
    
    func buildFormView(form: Form) -> UIView {
        let formView = FormView()
        let blockView = createBlockView(form.block, formView: formView)
        
        formView.addSubview(blockView)
        blockView.snp_makeConstraints { [unowned formView] (make) -> Void in
            make.edges.equalTo(formView)
        }
        
        return formView
    }

    
    // MARK: - Private 
    
    private func createBlockView(block: Block, formView: FormView) -> UIView {
        let blockView = UIView()
        
        if let s = block.block.first {
            var prevView = placeStatement(s, formView: formView, superView: blockView, prevView: nil, nextView: block.block.count == 1 ? blockView : nil)
            
            if block.block.count > 1 {
                for i in 1...(block.block.count-1) {
                    prevView = placeStatement(block.block[i], formView: formView, superView: blockView, prevView: prevView, nextView: block.block.count == i+1 ? blockView : nil)
                }
            }
        }
        
        return blockView
    }
    
    private func placeStatement(statement: Statement, formView: FormView, superView: UIView, prevView: UIView?, nextView: UIView?) -> UIView {
        let statementView = createStatementView(statement, formView: formView)
        
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
    
    private func createStatementView(statement: Statement, formView: FormView) -> UIView {
        return statement.accept(self, param: formView)
    }
    
    private func createConditionalView(conditional: Conditional, formView: FormView) -> UIView {
        return createBlockView(conditional.ifBlock, formView: formView)
    }
    
    
    // MARK: - Visitor functions
    
    override func visit(node: Question, param delegate: GenericParam) -> GenericReturn {
        return self.viewFactory.createQuestionView(node, delegate: delegate)
    }
    
    override func visit(node: Conditional, param delegate: GenericParam) -> GenericReturn {
        return self.createBlockView(node.ifBlock, formView: delegate) // TODO: fix this
    }
    
    override func visit(node: Block, param delegate: GenericParam) -> GenericReturn {
        return self.createBlockView(node, formView: delegate)
    }
}