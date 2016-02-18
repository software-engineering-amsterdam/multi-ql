//
//  UIView.swift
//  QLSwift
//
//  Created by Tom van Duist on 12/02/16.
//
//

import UIKit

import SnapKit

extension UIView {
    
    convenience init(nibName: String) {
        self.init(nibName: nibName, owner: nil)
    }
    
    convenience init(nibName: String, owner: AnyObject?) {
        self.init()
        
        self.setViewWithNib(nibName, owner: owner)
    }
    
    func setViewWithNib(nibName: String, owner: AnyObject?) {
        let owner: AnyObject? = (owner != nil) ? owner : self
        
        if let view: UIView? = UINib(nibName: nibName, bundle: nil).instantiateWithOwner(owner, options: nil).first as? UIView {
            self.addSubview(view!)
            
            view!.snp_makeConstraints { (make) -> Void in
                make.edges.equalTo(view!.superview!)
            }
        }
    }
}