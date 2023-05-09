package com.dev.noteappkmm.data.dataSource

import com.dev.noteappkmm.data.mapper.toNote
import com.dev.noteappkmm.database.NoteDatabase
import com.dev.noteappkmm.domain.dataSource.NoteDataSource
import com.dev.noteappkmm.domain.models.Note
import com.dev.noteappkmm.domain.time.DateTimeUtil
import database.NoteEntity

class NoteDataSourceImpl(
    db: NoteDatabase
): NoteDataSource {
    private val queries = db.noteQueries

    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            id = note.id,
            title = note.title,
            content = note.content,
            colorHex = note.colorHex,
            created = DateTimeUtil.toEpochMillis(note.created)
        )
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries
            .getNoteById(id)
            .executeAsOneOrNull()
            ?.toNote()
    }

    override suspend fun getAllNotes(): List<Note> {
        return queries
            .getAllNotes()
            .executeAsList()
            .map { it.toNote() }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries
            .deleteNoteById(id)
    }
}