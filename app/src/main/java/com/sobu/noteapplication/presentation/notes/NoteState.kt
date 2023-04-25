package com.sobu.noteapplication.presentation.notes

import com.sobu.noteapplication.domain.model.Note

data class NoteState(
    val notes: List<Note> = emptyList()
)