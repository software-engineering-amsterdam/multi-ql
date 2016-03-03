//
//  Form.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import UIKit


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
        catch let error as SemanticError {
            print(error)
            displayErrors(error)
        }
        catch let error {
            print(error)
            displayErrors(error)
        }
    }
    
    private func displayForm(form: QLForm) {
        self.navigationController?.pushViewController(FormViewController(form: form), animated: true)
    }
    
    private func displayErrors(error: SemanticError) {
        showAlerts(arg: error, confirmBlock: nil)
    }
    
    private func displayErrors(error: ErrorType) {
        showAlert("Error", message: "\(error)", cancelButton: nil, confirmButton: "Ok")
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
    private func showAlerts(arg error: SemanticError, confirmBlock: (() -> Void)? = nil) -> Bool {
        if case SemanticError.None = error {
            return false
        }
        
        switch error {
            case .Collection(let errors): return showAlerts("Error", message: errors.map { "\($0)" }, cancelButton: nil, confirmButton: "Ok", cancelBlock: nil, confirmBlock: confirmBlock)
            default: return showAlert("Error", message: "\(error)", cancelButton: nil, confirmButton: "Ok", cancelBlock: nil, confirmBlock: confirmBlock)
        }
    }
    
    private func showAlerts(arg warnings: [SemanticWarning], cancelBlock: (() -> Void)? = nil, confirmBlock: (() -> Void)? = nil) -> Bool {
        let warningMessages: [String] = warnings.map { "\($0)" }
        
        return showAlerts("warning", message: warningMessages, cancelButton: "Cancel", confirmButton: "Continue", cancelBlock: cancelBlock, confirmBlock: confirmBlock)
    }
    
    private func showAlerts(arg warning: SemanticWarning, cancelBlock: (() -> Void)? = nil, confirmBlock: (() -> Void)? = nil) -> Bool {
        return showAlerts(arg: [warning], cancelBlock: cancelBlock, confirmBlock: confirmBlock)
    }
}