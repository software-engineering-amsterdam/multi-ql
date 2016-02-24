//
//  BaseViewController.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import UIKit

class BaseViewController: UIViewController {
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: NSBundle?) {
        var nibName = nibNameOrNil
        
        if (nibNameOrNil == nil) {
            let className = NSStringFromClass(self.dynamicType).componentsSeparatedByString(".").last
            nibName = className?.stringByReplacingOccurrencesOfString("Controller", withString: "")
        }
        
        super.init(nibName: nibName, bundle: nibBundleOrNil)
    }
}
