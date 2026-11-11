import SwiftUI
import SharedLogic

extension PlatformScreen {
    
    @MainActor
    class PlatformViewModelWrapper: ObservableObject {
        let viewModel : PlatformViewModel
        
        
        init() {
            viewModel = PlatformInjector().viewModel
            state = viewModel.state.value
        }
        
        @Published var state : PlatformUiState
        
        func startObserving() {
            Task {
                for await state in viewModel.state {
                    self.state = state
                }
            }
        }
    }
}

struct PlatformScreen: View {
    
    @ObservedObject private(set) var viewModel: PlatformViewModelWrapper
    
    var body: some View {
        VStack(alignment: .center, spacing: 8){
            AppBar(text: String(localized: "platform_screen_title"))
                .padding(.vertical, 32)
            
            let info = viewModel.state.info
            if !info.isEmpty {
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(info, id: \.self) { item in
                            PlatformItemDisplay(item: item)
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

struct PlatformItemDisplay : View {
    
    var item: PlatformInfoItem
    
    var body: some View {
        VStack(alignment:.leading){
            Text(item.type.localizedLabel)
                .font(.title3)
                .fontWeight(.bold)
            Text(item.value)
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 8)
        .frame(maxWidth: .infinity, alignment: .leading)
    }
}
