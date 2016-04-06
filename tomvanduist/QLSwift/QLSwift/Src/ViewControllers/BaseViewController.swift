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
    
    internal func showAlerts(alerts: [Alert]) -> Bool {
        guard let alert = alerts.first
            else { return false }
        
        // Until the last message, show the next one upon confirm
        if alert != alerts.last {
            let remainingAlerts = alerts.filter { alert != $0 }
            
            showAlert(Alert(title: alert.title,
                message: alert.message,
                cancelButton: alert.cancelButton,
                confirmButton: alert.confirmButton,
                cancelBlock: alert.cancelBlock,
                confirmBlock: { [unowned self] in
                    self.showAlerts(remainingAlerts)
                })
            )
        } else {
            showAlert(alert)
        }
        
        return true
    }
    
    internal func showAlert(alert: Alert) -> Bool {
        DTAlertView(
            block: { (alertView, index, n) -> Void in
                if index == n {
                    if alert.cancelBlock != nil {
                        alert.cancelBlock!()
                    }
                }
                else {
                    if alert.confirmBlock != nil {
                        alert.confirmBlock!()
                    }
                }
            },
            title: alert.title,
            message: alert.message,
            cancelButtonTitle: alert.confirmBlock != nil ? alert.cancelButton : nil,
            positiveButtonTitle: alert.confirmBlock != nil ? alert.confirmButton : "Ok"
            ).show()
        
        return true
    }

}
