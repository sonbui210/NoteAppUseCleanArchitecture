package com.sobu.noteapplication.domain.use_case

import com.sobu.noteapplication.domain.model.Note
import com.sobu.noteapplication.domain.repository.NoteRepository

class GetNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id: Int) : Note? {
        return noteRepository.getNoteById(id)
    }
}