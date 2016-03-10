//
//  TestBed.swift
//  QLSwift
//
//  Created by Julian Jansen on 05-03-16.
//  Copyright © 2016 Julian Jansen. All rights reserved.
//

import Foundation
import SwiftParsec


/// Test setup based on Lynda.com + YouTube + WikiPedia.

/// Abstract visitor.
protocol Visitor {
    func visit(house: House)
}


// Abstract visitable.
protocol Visitable {
    func accept(visitor: Visitor)
}

class House: Visitable {
    
    /// Interface to accept visitor.
    func accept(visitor: Visitor) {
        
        // Triggers the visiting operation.
        visitor.visit(self)
    }
    
    func workOnHvac(hvacSpecialist: HvacSpecialist) {
        
        // We now have a reference to the HVAC specialist object in the house object.
        print("Worked on by \(hvacSpecialist)")
    }
    
    func workOncElectricity(electrician: Electrician) {
        
        // We now have a reference to the electrician object in the house object.
        print("Worked on by \(electrician)")
    }
}



/// Concrete visitor: HvacSpecialist.
class HvacSpecialist: Visitor {
    
    func visit(house: House) {
        // The visitor now has a reference to the house object.
        house.workOnHvac(self)
    }
    
}

/// Concrete visitor: Electrician.
class Electrician: Visitor {
    
    func visit(house: House) {
        // The visitor now has a reference to the house object.
        house.workOncElectricity(self)
    }
    
}


func testVisitorPattern() {
    
    // Create a HVAC specialist.

    let hv = HvacSpecialist()

    // Create an electrician.

    let e = Electrician()

    // Create a house.

    let home = House()

    // Let the house accept the HVAC specialist and work on the house by invoking the visit() method.

    home.accept(hv)

    // Let the house accept the electrician and work on the house by invoking the visit() method.

    home.accept(e)

}