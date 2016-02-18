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
            
            let form = try parser.parse(ql)
            displayForm(form)
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
        
//        self.showForm(kForm1)
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