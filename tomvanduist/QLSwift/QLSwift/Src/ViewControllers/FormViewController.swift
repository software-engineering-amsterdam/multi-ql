//
//  FormViewController.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import UIKit

class FormViewController: BaseViewController {
    let form: Form
    
    init(form: Form) {
        self.form = form
        
        super.init(nibName: "FormView", bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("NSCoding not supported!")
    }
}
