package com.dev.noteappkmm.android.note_list

import com.dev.noteappkmm.domain.models.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)
