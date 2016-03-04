//
//  FormViewController.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import UIKit
import SnapKit

class FormViewController: BaseViewController {
    @IBOutlet weak var contentView: UIView!
    
    let form: QLForm
    
    
    init(form: QLForm) {
        self.form = form
        
        super.init(nibName: "FormView", bundle: nil)
        
        self.title = "\(form.identifier.id)"
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("StoryBoards are not supported!")
    }
}

extension FormViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
//        let formViewFactory = DefaultFormViewFactory(formLayout: DefaultFormLayout())
//        let formViewBuilder = DefaultFormViewBuilder(formViewFactory: formViewFactory)
//        
//        let view = formViewBuilder.buildFormView(self.form)
//        
//        self.contentView.addSubview(view)
//        view.snp_makeConstraints { [unowned self] (make) -> Void in
//            make.edges.equalTo(self.contentView)
//        }
    }
}

