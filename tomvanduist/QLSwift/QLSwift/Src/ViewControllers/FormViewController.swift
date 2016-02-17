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
        
        super.init(nibName: "FormView", bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("NSCoding not supported!")
    }
}

extension FormViewController {
    
    override func viewDidLoad() {
        reloadView()
        
        super.viewDidLoad()
    }
    
    private func reloadView() {
        placeStatement(form.statement, superView: contentView, prevView: nil, nextView: contentView)
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
        return QuestionTextView(nibName: "QuestionTextView")
    }
    
    private func createView(statement: Conditional) -> QuestionView {
        return QuestionTextView(nibName: "QuestionTextView")
    }
    
    private func createView(statement: Block) -> QuestionView {
        return QuestionTextView(nibName: "QuestionTextView")
    }
}

