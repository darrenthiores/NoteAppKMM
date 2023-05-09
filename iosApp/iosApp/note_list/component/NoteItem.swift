import SwiftUI
import shared

struct NoteItem: View {
    var note: Note
    let onDeleteClick: () -> Void
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Text(note.title)
                    .font(.title3)
                    .fontWeight(.semibold)
                
                Spacer()
                
                Image(systemName: "xmark")
                    .onTapGesture {
                        onDeleteClick()
                    }
            }.padding(.bottom, 4)
            
            Text(note.content)
                .fontWeight(.light)
                .padding(.bottom, 4)
            
            HStack {
                Spacer()
                
                Text(
                    DateTimeUtil()
                        .formatNoteDate(
                            dateTime: note.created
                        )
                )
                .font(.footnote)
                .fontWeight(.light)
            }
        }
        .padding()
        .background(Color(hex: note.colorHex))
        .clipShape(RoundedRectangle(cornerRadius: 4.0))
    }
}

struct NoteItem_Previews: PreviewProvider {
    static var previews: some View {
        NoteItem(
            note: Note(
                id: nil,
                title: "My Note",
                content: "My Content",
                colorHex: 0xFF2341,
                created: DateTimeUtil().now()
            ),
            onDeleteClick: {}
        )
    }
}
