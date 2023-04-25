package com.sobu.noteapplication.domain.use_case

import com.sobu.noteapplication.domain.model.Note
import com.sobu.noteapplication.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotes(
    private val noteRepository: NoteRepository
) {
    operator fun invoke():Flow<List<Note>> = noteRepository.getNotes()
}