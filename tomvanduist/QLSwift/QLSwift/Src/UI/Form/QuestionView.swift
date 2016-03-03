//
//  QuestionView.swift
//  QLSwift
//
//  Created by Tom van Duist on 12/02/16.
//
//

import UIKit

class QuestionView: BaseView, ViewContainable {
    internal let viewContainer = BaseView()
    
    
    convenience init(layout: Layout, question: Question, widget: ViewWidget) {
        self.init(frame: CGRectZero)
        
        setupView(layout, question: question.label, widget: widget)
    }
    
    private func setupView(layout: Layout, question: String, widget: ViewWidget) {
        self.viewContainer.addSubview(widget)
        widget.snp_makeConstraints { [unowned viewContainer] (make) -> Void in
            make.edges.equalTo(viewContainer)
        }
        
        self.addSubview(viewContainer)
        viewContainer.snp_makeConstraints { [unowned self] (make) -> Void in
            make.left.equalTo(self.snp_left)
            make.right.equalTo(self.snp_right)
            make.bottom.equalTo(self.snp_bottom)
        }
        
        
        let label = UILabel()
        label.text = question
        
        self.addSubview(label)
        
        label.snp_makeConstraints { [unowned self, viewContainer] (make) -> Void in
            make.top.equalTo(self.snp_top).offset(layout.margin.top)
            make.left.equalTo(self.snp_left).offset(layout.margin.left)
            make.right.equalTo(self.snp_right).offset(layout.margin.right)
            make.bottom.equalTo(viewContainer.snp_top).offset(-layout.margin.bottom)
        }
    }
}
