//
//  BaseViewController.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import UIKit
import DTAlertView


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


extension BaseViewController {
    
    internal func showAlerts(title: String, message messages: [String], cancelButton: String?, confirmButton: String, cancelBlock: (() -> Void)? = nil, confirmBlock: (() -> Void)? = nil) -> Bool {
        var _confirmBlock: (() -> Void)?
        
        guard let message = messages.first
            else { return false }
        
        // Until the last message, show the next one upon confirm
        if message != messages.last {
            let remainingMessages = messages.filter { message != $0 }
            
            _confirmBlock = { [unowned self] in
                self.showAlerts(title,
                    message: remainingMessages,
                    cancelButton: cancelButton,
                    confirmButton: confirmButton,
                    cancelBlock: cancelBlock,
                    confirmBlock: confirmBlock
                )
            }
        } else {
            _confirmBlock = confirmBlock
        }
        
        showAlert(title,
            message: message,
            cancelButton: cancelButton,
            confirmButton: confirmButton,
            cancelBlock: cancelBlock,
            confirmBlock: _confirmBlock
        )
        
        return true
    }
    
    internal func showAlert(title: String, message: String, cancelButton: String?, confirmButton: String, cancelBlock: (() -> Void)? = nil, confirmBlock: (() -> Void)? = nil) -> Bool {
        DTAlertView(
            block: { (alertView, index, n) -> Void in
                if index == n {
                    if cancelBlock != nil {
                        cancelBlock!()
                    }
                }
                else {
                    if confirmBlock != nil {
                        confirmBlock!()
                    }
                }
            },
            title: title,
            message: message,
            cancelButtonTitle: confirmBlock != nil ? cancelButton : nil,
            positiveButtonTitle: confirmBlock != nil ? confirmButton : "Ok"
            ).show()
        
        return true
    }

}
