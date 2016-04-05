//
//  Form.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import UIKit


class ChooseFormViewController: BaseViewController {
    
    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)
        
//        self.showForm(kForm1)
    }
    
    func showForm(formName: String) {
        do {
            let ql = try QL(qlFromFileNamed: formName)
            
            let (questionnaire, warnings) = try ql.toQuestionnaire()
            
            if warnings.isEmpty {
                self.display(questionnaire)
            } else {
                showAlerts(arg: warnings, cancelBlock: nil, confirmBlock: { [unowned self] in
                    self.display(questionnaire)
                })
            }
        }
        catch let error as SemanticErrorCollection {
            displayErrors(error)
        }
        catch let error as SemanticError {
            displayErrors(error)
        }
        catch let error {
            displayErrors(error)
        }
    }
    
    private func display(questionnaire: Questionnaire) {
        self.navigationController?.pushViewController(QuestionnaireViewController(questionnaire: questionnaire), animated: true)
    }
    
    private func displayErrors(error: SemanticErrorCollection) {
        showAlerts(arg: error.errors, confirmBlock: nil)
    }
    
    private func displayErrors(error: SemanticError) {
        showAlerts(arg: error, confirmBlock: nil)
    }
    
    private func displayErrors(error: ErrorType) {
        showAlert(Alert(title: "Error", message: "\(error)", cancelButton: nil, confirmButton: "Ok"))
    }
}


// MARK: - IBActions

extension ChooseFormViewController {
    @IBAction func simpleFormPressed(sender: UIButton) {
        self.showForm("SimpleForm")
    }
    
    @IBAction func complexFormPressed(sender: UIButton) {
        self.showForm("ComplexForm")
    }
    
    @IBAction func errorFormPressed(sender: UIButton) {
        self.showForm("ErrorForm")
    }
    
    @IBAction func warningFormPressed(sender: UIButton) {
        self.showForm("WarningForm")
    }
}


// MARK: - Alerts

extension ChooseFormViewController {
    private func showAlerts(arg errors: [SemanticError], confirmBlock: (() -> Void)? = nil) -> Bool {
        let alerts: [Alert] = errors.map { error in
            Alert(title: "Error", message: "\(error)", cancelButton: nil, confirmButton: "Ok", cancelBlock: nil, confirmBlock: confirmBlock)
        }
        
        return showAlerts(alerts)
    }
    
    private func showAlerts(arg error: SemanticError, confirmBlock: (() -> Void)? = nil) -> Bool {
        return showAlerts(arg: [error], confirmBlock: confirmBlock)
    }
    
    private func showAlerts(arg warnings: [SemanticWarning], cancelBlock: (() -> Void)? = nil, confirmBlock: (() -> Void)? = nil) -> Bool {
        let alerts: [Alert] = warnings.map { warning in
            Alert(title: "Warning", message: "\(warning)", cancelButton: "Cancel", confirmButton: "Continue", cancelBlock: cancelBlock, confirmBlock: confirmBlock)
        }
        
        return showAlerts(alerts)
    }
    
    private func showAlerts(arg warning: SemanticWarning, cancelBlock: (() -> Void)? = nil, confirmBlock: (() -> Void)? = nil) -> Bool {
        return showAlerts(arg: [warning], cancelBlock: cancelBlock, confirmBlock: confirmBlock)
    }
}