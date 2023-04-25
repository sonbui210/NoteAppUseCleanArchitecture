package com.sobu.noteapplication.domain.use_case

import com.sobu.noteapplication.domain.model.Note
import com.sobu.noteapplication.domain.repository.NoteRepository

class DeleteNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        noteRepository.deleteNote(note)
    }
}