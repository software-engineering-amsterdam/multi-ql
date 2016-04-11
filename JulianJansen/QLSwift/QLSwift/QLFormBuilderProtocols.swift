//
//  QLFormBuilderProtocols.swift
//  QLSwift
//
//  Created by Julian Jansen on 07-04-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation

protocol FormBuilderVisitor {
    func visit(qlform: QLForm)
    func visit(qlquestion: QLQuestion)
    func visit(qlifstatement: QLIfStatement)
}

protocol FormBuilderVisitable {
    func accept(visitor: FormBuilderVisitor)
}