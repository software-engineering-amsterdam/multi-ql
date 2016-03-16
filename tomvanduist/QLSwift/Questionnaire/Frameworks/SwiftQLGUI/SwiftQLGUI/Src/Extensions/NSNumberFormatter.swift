//
//  NSNumberFormatter.swift
//  QLSwift
//
//  Created by Tom van Duist on 11/03/16.
//
//

import Foundation

extension NSNumberFormatter {
    
    static func localizedFloatingPointFormatter() -> NSNumberFormatter {
        let numberFormatter = NSNumberFormatter()
        numberFormatter.numberStyle = NSNumberFormatterStyle.DecimalStyle
        numberFormatter.locale = NSLocale.currentLocale()
        numberFormatter.maximumFractionDigits = NSInteger(UInt8.max)
        return numberFormatter
    }
}