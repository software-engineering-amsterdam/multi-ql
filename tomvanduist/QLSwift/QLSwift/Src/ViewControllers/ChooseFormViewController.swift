//
//  Form.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import UIKit
import DTAlertView

let kForm1 = "form"
let kForm2 = "form2"

class ChooseFormViewController: BaseViewController {
    
    func showForm(formName: String) {
        do {
            let ql = try QL(qlFromFileNamed: formName)
            let parser = Parser()
            
            let (form, warnings) = try parser.parse(ql)
            if warnings.isEmpty {
                self.displayForm(form)
            } else {
                showAlerts(arg: warnings, cancelBlock: nil, confirmBlock: { [unowned self] in
                    self.displayForm(form)
                })
            }
        }
        catch let error {
            print(error)
            displayErrors(error)
        }
    }
    
    private func displayForm(form: Form) {
        self.navigationController?.pushViewController(FormViewController(form: form), animated: true)
    }
    
    private func displayErrors(error: ErrorType) {
        // TODO: implement error view
    }
    
    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)
        
        self.showForm(kForm1)
    }
}


// MARK: - IBActions

extension ChooseFormViewController {
    @IBAction func form1Pressed(sender: UIButton) {
        self.showForm(kForm1)
    }
    
    @IBAction func form2Pressed(sender: UIButton) {
        self.showForm(kForm2)
    }
}


// MARK: - Alerts

extension ChooseFormViewController {
    private func showAlerts(arg error: SemanticError, cancelBlock: (() -> Void)? = nil, confirmBlock: (() -> Void)? = nil) -> Bool {
        if case SemanticError.None = error {
            return false
        }
        
        // todo:
        return true
    }
    
    private func showAlerts(arg warnings: [SemanticWarning], cancelBlock: (() -> Void)? = nil, confirmBlock: (() -> Void)? = nil) -> Bool {
        if warnings.isEmpty {
            return false
        }
        
        for warning in warnings {
            if warning == warnings.last! {
                showAlerts(arg: warning, cancelBlock: cancelBlock, confirmBlock: confirmBlock)
            } else {
                showAlerts(arg: warning, cancelBlock: nil, confirmBlock: nil)
            }
        }
        
        return true
    }
    
    private func showAlerts(arg warning: SemanticWarning, cancelBlock: (() -> Void)? = nil, confirmBlock: (() -> Void)? = nil) -> Bool {
        DTAlertView(
            block: { (alertView, index, n) -> Void in
                if index == 0 {
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
            title: "Warning",
            message: "\(confirmBlock)",
            cancelButtonTitle: "Cancel",
            positiveButtonTitle: "Confirm"
        ).show()
        
        return true
    }
}