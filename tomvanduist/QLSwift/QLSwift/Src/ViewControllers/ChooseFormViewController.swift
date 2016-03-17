//
//  Form.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import UIKit


let kSimpleForm = "SimpleForm"
let kComplexForm = "ComplexForm"


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
        showAlert("Error", message: "\(error)", cancelButton: nil, confirmButton: "Ok")
    }
}


// MARK: - IBActions

extension ChooseFormViewController {
    @IBAction func form1Pressed(sender: UIButton) {
        self.showForm(kSimpleForm)
    }
    
    @IBAction func form2Pressed(sender: UIButton) {
        self.showForm(kComplexForm)
    }
}


// MARK: - Alerts

extension ChooseFormViewController {
    private func showAlerts(arg errors: [SemanticError], confirmBlock: (() -> Void)? = nil) -> Bool {
        let errorMessages: [String] = errors.map { "\($0)" }
        
        return showAlerts("Error", message: errorMessages, cancelButton: nil, confirmButton: "Ok", cancelBlock: nil, confirmBlock: confirmBlock)
    }
    
    private func showAlerts(arg error: SemanticError, confirmBlock: (() -> Void)? = nil) -> Bool {
        return showAlerts(arg: [error], confirmBlock: confirmBlock)
    }
    
    private func showAlerts(arg warnings: [SemanticWarning], cancelBlock: (() -> Void)? = nil, confirmBlock: (() -> Void)? = nil) -> Bool {
        let warningMessages: [String] = warnings.map { "\($0)" }
        
        return showAlerts("Warning", message: warningMessages, cancelButton: "Cancel", confirmButton: "Continue", cancelBlock: cancelBlock, confirmBlock: confirmBlock)
    }
    
    private func showAlerts(arg warning: SemanticWarning, cancelBlock: (() -> Void)? = nil, confirmBlock: (() -> Void)? = nil) -> Bool {
        return showAlerts(arg: [warning], cancelBlock: cancelBlock, confirmBlock: confirmBlock)
    }
}