//
//  FormViewController.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/02/16.
//
//

import UIKit
import SnapKit

class QuestionnaireViewController: BaseViewController {
    @IBOutlet weak var contentView: UIView!
    
    let questionnaire: Questionnaire
    
    
    init(questionnaire: Questionnaire) {
        self.questionnaire = questionnaire
        
        super.init(nibName: "FormView", bundle: nil)
        
        self.title = "\(questionnaire.title)"
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("StoryBoards are not supported!")
    }
}

extension QuestionnaireViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let view = QuestionnaireViewBuilder().build(questionnaire)
        
        self.contentView.addSubview(view)
        view.snp_makeConstraints { [unowned self] (make) -> Void in
            make.edges.equalTo(self.contentView)
        }
    }
}

