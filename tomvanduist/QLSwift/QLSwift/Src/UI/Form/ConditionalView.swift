//
//  ConditionalView.swift
//  QLSwift
//
//  Created by Tom van Duist on 18/02/16.
//
//

import UIKit

class ConditionalView: BaseView, ViewContainable {
    let viewContainer = BaseView()
    let heightConstraint: NSLayoutConstraint
    
    private let conditional: Conditional
    
    init(conditional: Conditional) {
        self.conditional = conditional
        heightConstraint = NSLayoutConstraint(item: viewContainer, attribute: .Height, relatedBy: .Equal, toItem: nil, attribute: .NotAnAttribute, multiplier: 1.0, constant: 0)
        
        super.init(frame: CGRectZero)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("StoryBoards not supported!")
    }
    
    override func initialize() {
        super.initialize()
        
        self.addSubview(viewContainer)
        viewContainer.snp_makeConstraints { [unowned self] (make) -> Void in
            make.edges.equalTo(self)
        }
        
        viewContainer.addConstraint(heightConstraint)
        
        reloadView()
    }
    
    override func reloadView() {
        super.reloadView()

        self.heightConstraint.active = !self.conditional.isSatisfied()
        
        UIView.animateWithDuration(kAnimationDuration) {
            self.layoutIfNeeded()
        }
    }
}