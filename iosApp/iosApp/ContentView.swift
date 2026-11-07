import SwiftUI
import SharedLogic

struct ContentView: View {
    
    @State private var shouldOpenAbout = false
    @State private var shouldOpenSources = false
    
    var body: some View {
        let platformInfo = PlatformInfoScreen()
        let articlesScreen = ArticlesScreen (viewModel: .init())
        let sourcesScreen = SourcesScreen(viewModel: .init())
        
        NavigationStack {
            articlesScreen
                .toolbar {
                    ToolbarItem {
                        HStack {
                            Button {
                                shouldOpenSources = true
                            } label: {
                                Label("Sources", systemImage:"note").labelStyle(.iconOnly)
                            }
                            .navigationDestination(isPresented: $shouldOpenSources){
                                sourcesScreen
                            }
                            
                            Button {
                                shouldOpenAbout = true
                            } label: {
                                Label("About", systemImage:"info.circle").labelStyle(.iconOnly)
                            }
                            .sheet(isPresented: $shouldOpenAbout){
                                platformInfo
                            }
                        }
                    }
                }
                .refreshable {
                    articlesScreen.viewModel.articlesViewModel.getArticles(forceFetch: true)
                }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
