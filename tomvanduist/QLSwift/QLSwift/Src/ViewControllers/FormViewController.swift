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
        
        //TODO:
        guard let context = try? Context(form: form)
            else { return }
        
        let questionnaire = QuestionnaireBuilder().build(form, context: context)
        
        let view = QuestionnaireViewBuilder().build(questionnaire)
        
        self.contentView.addSubview(view)
        view.snp_makeConstraints { [unowned self] (make) -> Void in
            make.edges.equalTo(self.contentView)
        }
    }
}

