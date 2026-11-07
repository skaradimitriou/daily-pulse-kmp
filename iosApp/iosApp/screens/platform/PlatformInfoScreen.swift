import SwiftUI
import SharedLogic

struct PlatformInfoScreen: View {
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8){
            Text("Text holder")
                .font(.title)
                .fontWeight(.bold)
            Text("Text value")
            Text("Text holder")
                .font(.title)
                .fontWeight(.bold)
            Text("Text value")
        }
    }
}

struct PlatformInfoPreview: PreviewProvider {
    static var previews: some View {
        PlatformInfoScreen()
    }
}
