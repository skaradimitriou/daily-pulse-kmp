import SwiftUI
import SharedLogic

extension ArticlesScreen {
    
    @MainActor
    class ArticlesViewModelWrapper: ObservableObject {
        let articlesViewModel : ArticlesViewModel
        
        
        init() {
            articlesViewModel = ArticlesInjector().articlesViewModel
            articlesState = articlesViewModel.articlesState.value
        }
        
        @Published var articlesState : ArticlesState
        
        func startObserving() {
            Task {
                for await state in articlesViewModel.articlesState {
                    self.articlesState = state
                }
            }
        }
    }
}

struct ArticlesScreen : View {
    
    @ObservedObject private(set) var viewModel: ArticlesViewModelWrapper
    
    var body: some View {
        VStack {
            AppBar(text: String(localized: "articles_screen_title") )
            
            if viewModel.articlesState.isLoading {
                Loader()
            }
            
            if let error = viewModel.articlesState.error {
                ErrorMessage(message : error)
            }
            
            if !viewModel.articlesState.articles.isEmpty {
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.articlesState.articles, id: \.self) { article in
                            ArticleItemView(article: article)
                        }
                    }
                }
            }
        }
        .onAppear {
            self.viewModel.startObserving()
        }
    }
}

struct ArticleItemView : View {
    
    var article: Article
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8){
            AsyncImage(url: URL(string:article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image Load Error")
                } else {
                    ProgressView()
                }
            }
            Text(article.title)
                .font(.title)
                .fontWeight(.bold)
            Text(article.description)
            Text(article.displayDate).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }
    }
}

struct Loader : View {
    
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage : View {
    
    var message: String
    
    var body: some View {
        Text(message)
            .font(.title)
    }
}
