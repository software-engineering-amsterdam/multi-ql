//
//  FormViewController.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import UIKit
import SnapKit

class FormViewController: BaseViewController {
    @IBOutlet weak var contentView: UIView!
    
    let form: Form
    
    
    init(form: Form) {
        self.form = form
//        print((form.block.block[2] as! Question).expression.eval())
        super.init(nibName: "FormView", bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("NSCoding not supported!")
    }
}

extension FormViewController {
    
    override func viewDidLoad() {
//        reloadView()
        
//        let widget = BooleanView(layout: DefaultFormLayout().widgetLayout)
//        
//        let questionView = QuestionView(layout: DefaultFormLayout().questionLayout, question: "1) Question?", widget: widget)
//        questionView.backgroundColor = UIColor.redColor()
//        
//        
//        
//        
//        
//        self.contentView.addSubview(questionView)
//        questionView.snp_makeConstraints { (make) -> Void in
//            make.edges.equalTo(contentView)
//        }
        
        
        
        let formViewFactory = ConcreteFormViewFactory(formLayout: DefaultFormLayout())
        
        let view = formViewFactory.createFormView(self.form)
        
        self.view.addSubview(view)
        
        view.snp_makeConstraints { [unowned self] (make) -> Void in
            make.edges.equalTo(self.view)
        }
        
        
        super.viewDidLoad()
    }
    
    private func reloadView() {
        placeStatement(form.block, superView: contentView, prevView: nil, nextView: contentView)
    }
    
    private func placeStatement(statement: Statement, superView: UIView, prevView: UIView?, nextView: UIView?) -> UIView {
        var newView: UIView = UIView()
        
        if let question = statement as? Question {
            newView = createView(question)
        } else if let conditional = statement as? Conditional {
            newView = createView(conditional)
        } else if let block = statement as? Block {
            var prevView: UIView?
            
            if let s = block.block.first {
                prevView = placeStatement(s, superView: newView, prevView: nil, nextView: block.block.count == 1 ? newView : nil)
                
                if block.block.count > 1 {
                    for i in 1...(block.block.count-1) {
                        prevView = placeStatement(block.block[i], superView: newView, prevView: prevView!, nextView: block.block.count == i+1 ? newView : nil)
                    }
                }
            }
            
            
        }
        
        
        superView.addSubview(newView)
        
        newView.snp_updateConstraints { [unowned superView, prevView, nextView] (make) -> Void in
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
        
        return newView
    }
    
    private func createView(statement: Question) -> QuestionView {
//        if let expr = statement.expression as? BooleanField {
//            let factory = ConcreteFormViewFactory(formLayout: DefaultFormLayout())
////            return factory.createView(expr)
//        }
        return QuestionView(nibName: "QuestionTextView")
    }
    
    private func createView(statement: Conditional) -> QuestionView {
        return QuestionView(nibName: "QuestionTextView")
    }
    
    private func createView(statement: Block) -> QuestionView {
        return QuestionView(nibName: "QuestionTextView")
    }
}

