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
    
    lazy var rootViewController: RootViewController = {
        return RootViewController(nibName:"RootView", bundle:nil)
    }()

    func application(application: UIApplication, didFinishLaunchingWithOptions launchOptions: [NSObject: AnyObject]?) -> Bool {
        window = UIWindow(frame: UIScreen.mainScreen().bounds)
        
        
        if let ql = try? QL(qlFromFileNamed: "form") {
            let parser = Parser()
            
            do {
                let form = try parser.parse(ql)
                print(form)
            }
            catch let error {
                print(error)
            }
        }
    
        
        if let window = window {
            window.rootViewController = rootViewController
            window.makeKeyAndVisible()
        }
        
        return true
    }
}