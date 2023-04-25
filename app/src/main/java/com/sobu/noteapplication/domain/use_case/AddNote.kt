package com.sobu.noteapplication.domain.use_case

import com.sobu.noteapplication.domain.model.Note
import com.sobu.noteapplication.domain.repository.NoteRepository

class AddNote(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            return
        }
        if (note.content.isBlank()) {
            return
        }
        noteRepository.insertNote(note)
    }
}