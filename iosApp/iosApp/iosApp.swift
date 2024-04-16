//
//  iosAppApp.swift
//  iosApp
//
//  Created by vdcast on 09.04.2024.
//

import SwiftUI
import shared

@main
struct iosApp: App {

    init() {
        IosKoin.shared.initialize(userDefaults: UserDefaults.standard)
        LogKt.doInitLogs()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
