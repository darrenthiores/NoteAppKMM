import SwiftUI
import shared

struct NoteDetailScreen: View {
    @StateObject private var viewModel = NoteDetailViewModel()
    @Environment(\.presentationMode) private var presentation
    let note: Note?
    
    var body: some View {
        VStack(alignment: .leading) {
            TextField(
                "Enter a title...",
                text: $viewModel.noteTitle
            )
            .font(.title)
            
            TextField(
                "Enter some content...",
                text: $viewModel.noteContent
            )
            
            Spacer()
        }
        .toolbar {
            Image(systemName: "checkmark")
                .onTapGesture {
                    Task {
                        do {
                            try await viewModel.saveNote()
                            presentation.wrappedValue.dismiss()
                        } catch {
                            print(error)
                        }
                    }
                }
                .foregroundColor(.black)
        }
        .padding()
        .background(Color(hex: viewModel.noteColor))
        .onAppear {
            viewModel.loadNoteIfExists(note: note)
        }
    }
}

struct NoteDetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        NoteDetailScreen(
            note: nil
        )
    }
}
