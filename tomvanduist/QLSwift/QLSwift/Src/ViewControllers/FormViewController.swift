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
        
        let formViewBuilder = DefaultFormViewBuilder()
        
        guard let symbolTable = try? TypeInferer().inferTypes(form)
            else { return }
        
        let context = QLContext(form: form, symbolTable: symbolTable)
        
        let view = formViewBuilder.buildFormView(self.form, context: context)
        
        self.contentView.addSubview(view)
        view.snp_makeConstraints { [unowned self] (make) -> Void in
            make.edges.equalTo(self.contentView)
        }
    }
}

