import SwiftUI

struct NoteListScreen: View {
    @StateObject private var viewModel = NoteListViewModel()
    
    var body: some View {
        NavigationView {
            VStack {
                ZStack {
                    HideAbleSearchTextField(
                        text: $viewModel.searchText,
                        isSearchActive: $viewModel.isSearchActive,
                        plusDestination: {
                            NoteDetailScreen(note: nil)
                        }
                    )
                    
                    if !viewModel.isSearchActive {
                        Text("All Notes")
                            .font(.title2)
                    }
                }
                .padding()
                
                List {
                    ForEach(
                        viewModel.filteredNotes,
                        id: \.self.id
                    ) { note in
                        NoteItem(
                            note: note,
                            onDeleteClick: {
                                viewModel
                                    .deleteNoteById(
                                        id: note.id?.int64Value
                                    )
                            }
                        )
                        .background (
                            NavigationLink(
                                "",
                                destination: NoteDetailScreen(note: note)
                            )
                            .opacity(0)
                        )
                        .listRowSeparator(.hidden)
                    }
                }
                .onAppear {
                    viewModel.loadNotes()
                }
                .listStyle(.plain)
            }
        }
    }
}

struct NoteListScreen_Previews: PreviewProvider {
    static var previews: some View {
        NoteListScreen()
    }
}
