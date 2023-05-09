import SwiftUI

struct HideAbleSearchTextField<Destination: View>: View {
    @Binding var text: String
    @Binding var isSearchActive: Bool
    let plusDestination: () -> Destination
    
    var body: some View {
        HStack {
            TextField(
                "Search...",
                text: $text
            )
            .textFieldStyle(.roundedBorder)
            .opacity(isSearchActive ? 1 : 0)
            
            Image(systemName: isSearchActive ? "xmark" : "magnifyingglass")
                .onTapGesture {
                    isSearchActive.toggle()
                    
                    if !isSearchActive {
                        text = ""
                    }
                }
            
            NavigationLink {
                plusDestination()
            } label: {
                Image(systemName: "plus")
            }
            .foregroundColor(.black)
        }
    }
}

struct HideAbleSearchTextField_Previews: PreviewProvider {
    static var previews: some View {
        HideAbleSearchTextField(
            text: .constant("fulaby note"),
            isSearchActive: .constant(true),
            plusDestination: { EmptyView() }
        )
    }
}
