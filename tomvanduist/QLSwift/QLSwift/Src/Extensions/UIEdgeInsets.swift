//
//  UIEdgeInsets.swift
//  QLSwift
//
//  Created by Tom van Duist on 21/02/16.
//
//

import UIKit

func UIEdgeInsetsMake(x: CGFloat, y: CGFloat) -> UIEdgeInsets {
    return UIEdgeInsets(top: y, left: x, bottom: y, right: x)
}