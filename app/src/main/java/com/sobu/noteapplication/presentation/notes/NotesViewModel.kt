package com.sobu.noteapplication.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobu.noteapplication.domain.model.Note
import com.sobu.noteapplication.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NoteState())
    val state : State<NoteState> = _state

    private var recentlyDeleteNote: Note? = null
    private var getNoteJob: Job? = null


    init {
        getNote()
    }
    private fun getNote() {
        getNoteJob?.cancel()

        noteUseCases.getNotes().onEach {
            _state.value = state.value.copy(
                notes = it
            )
        }.launchIn(viewModelScope)

    }


    fun onEvent(notesEvent: NotesEvent) {
        when (notesEvent) {
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(notesEvent.note)
                    recentlyDeleteNote = notesEvent.note
                }
            }

            is NotesEvent.ResoteNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeleteNote ?: return@launch)
                }
            }
        }
    }


}