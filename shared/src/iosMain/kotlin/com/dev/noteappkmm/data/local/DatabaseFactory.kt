package com.dev.noteappkmm.data.local

import com.dev.noteappkmm.database.NoteDatabase
import com.squareup.sqldelight.db.SqlDriver

object DatabaseFactory {

    fun createDatabase(driver: SqlDriver): NoteDatabase {
        return NoteDatabase(driver)
    }
}