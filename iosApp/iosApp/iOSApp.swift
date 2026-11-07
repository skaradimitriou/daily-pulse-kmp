import SwiftUI
import SharedLogic

@main
struct iOSApp: App {
    
    init() {
        KoinInitializerKt.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
