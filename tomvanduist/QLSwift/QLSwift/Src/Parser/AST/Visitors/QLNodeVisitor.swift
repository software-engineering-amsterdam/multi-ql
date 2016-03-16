//
//  QLNodeVisitor.swift
//  QLSwift
//
//  Created by Tom van Duist on 09/03/16.
//
//

import Foundation

protocol QLNodeVisitor: QLStatementVisitor, QLExpressionVisitor, QLLiteralVisitor, QLTypeVisitor {
}