//
//  AppDelegate.swift
//  QLSwift
//
//  Created by Tom van Duist on 03/02/16.
//
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?
    
    lazy var mainViewController: MainViewController = {
        return MainViewController(nibName:"MainView", bundle:nil)
    }()

    func application(application: UIApplication, didFinishLaunchingWithOptions launchOptions: [NSObject: AnyObject]?) -> Bool {
        window = UIWindow(frame: UIScreen.mainScreen().bounds)
        
        let ql = QLParser()
        ql.testCSV()
        ql.testJSON()
        ql.testQL()
        
        
        if let window = window {
            window.rootViewController = mainViewController
            window.makeKeyAndVisible()
        }
        
        return true
    }
}