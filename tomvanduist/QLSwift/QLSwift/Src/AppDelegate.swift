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
        if let form = try? ql.parse(String(stringFromFile: "form", ofType: "ql")) {
            print(form)
            let nForm = form.implode()
            print(nForm)
        }
        
        
        if let window = window {
            window.rootViewController = mainViewController
            window.makeKeyAndVisible()
        }
        
        return true
    }
}