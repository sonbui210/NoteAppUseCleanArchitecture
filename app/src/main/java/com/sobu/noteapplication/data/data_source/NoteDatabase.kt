package com.sobu.noteapplication.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sobu.noteapplication.domain.model.Note


@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DB_NAME = "note_database"
    }

}