package com.sobu.noteapplication.presentation.notes

import com.sobu.noteapplication.domain.model.Note

sealed class NotesEvent {
    data class DeleteNote(val note: Note) : NotesEvent()
    object ResoteNote: NotesEvent()
}