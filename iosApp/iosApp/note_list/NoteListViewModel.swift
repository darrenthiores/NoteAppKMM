import Foundation
import shared

extension NoteListScreen {
    @MainActor class NoteListViewModel: ObservableObject {
        @Inject private var noteDataSource: NoteDataSource
        private let searchNotes = SearchNotes()
        
        private var notes = [Note]()
        @Published private(set) var filteredNotes = [Note]()
        @Published var searchText = "" {
            didSet {
                filteredNotes = searchNotes.execute(
                    notes: notes,
                    query: searchText
                )
            }
        }
        @Published var isSearchActive = false
        
        func loadNotes() {
            Task {
                do {
                    notes = try await noteDataSource.getAllNotes()
                    filteredNotes = notes
                } catch {
                    print(error)
                }
            }
        }
        
        func deleteNoteById(id: Int64?) {
            if let noteId = id {
                Task {
                    do {
                        try await noteDataSource.deleteNoteById(id: noteId)
                        loadNotes()
                    } catch {
                        print(error)
                    }
                }
            }
        }
        
        
    }
}
