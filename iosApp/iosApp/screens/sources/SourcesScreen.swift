import SwiftUI
import SharedLogic

extension SourcesScreen {
    
    @MainActor
    class SourcesViewModelWrapper: ObservableObject {
        let viewModel : SourcesViewModel
        
        
        init() {
            viewModel = SourcesInjector().viewModel
            state = viewModel.state.value
        }
        
        @Published var state : SourcesUiState
        
        func startObserving() {
            Task {
                for await state in viewModel.state {
                    self.state = state
                }
            }
        }
    }
}

struct SourcesScreen : View {
    
    @ObservedObject private(set) var viewModel: SourcesViewModelWrapper
    
    var body: some View {
        VStack {
            let title = "Sources"
            AppBar(text: title )
            
            if viewModel.state.isLoading {
                Loader()
            }
            
            if let error = viewModel.state.error {
                ErrorMessage(message : error)
            }
            
            if !viewModel.state.sources.isEmpty {
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.state.sources, id: \.self) { source in
                            SourceItem(source: source)
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

struct SourceItem : View {
    
    var source: Source
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8){
            Text(source.title)
                .font(.title)
                .fontWeight(.bold)
            Text(source.subtitle)
            Text(source.languageWithCountry).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }
    }
}
