//
//  ViewController.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/02/16.
//
//

import UIKit

class RootViewController: BaseViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.navigationController?.setViewControllers([ChooseFormViewController(nibName: "ChooseFormView", bundle: nil)], animated: false)
    }
}