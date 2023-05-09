import Foundation
import shared

extension NoteDetailScreen {
    @MainActor class NoteDetailViewModel: ObservableObject {
        @Inject private var noteDataSource: NoteDataSource
        
        private var noteId: KotlinLong? = nil
        @Published var noteTitle = ""
        @Published var noteContent = ""
        @Published private(set) var noteColor = Note.Companion().generateRandomColor()
        
        func loadNoteIfExists(note: Note?) {
            if let selectedNote = note {
                noteId = selectedNote.id
                noteTitle = selectedNote.title
                noteContent = selectedNote.content
                noteColor = selectedNote.colorHex
            }
        }
        
        func saveNote() async throws {
            try await noteDataSource.insertNote(
                note: Note(
                    id: noteId,
                    title: noteTitle,
                    content: noteContent,
                    colorHex: noteColor,
                    created: DateTimeUtil().now()
                )
            )
        }
    }
}
