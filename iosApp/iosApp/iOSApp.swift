import SwiftUI
import SharedLogic

@main
struct iOSApp: App {
    
    init() {
        KoinInitializerKt.doInitKoin()
    }
    
    var body: some Scene {
        
        let articlesScreen = ArticlesScreen (viewModel: .init())
        
        WindowGroup {
            articlesScreen
                .refreshable {
                    articlesScreen.viewModel.articlesViewModel.getArticles(forceFetch: true)
                }
        }
    }
}
