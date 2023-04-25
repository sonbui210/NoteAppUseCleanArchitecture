package com.sobu.noteapplication.presentation.update_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcomentry.lib.from
import com.sobu.noteapplication.domain.model.Note
import com.sobu.noteapplication.domain.use_case.NoteUseCases
import com.sobu.noteapplication.presentation.navigation.UpdateScreenArgument
import com.sobu.noteapplication.presentation.util.AddEditNoteEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UpdateNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    init {
        val argument = UpdateScreenArgument().from(savedStateHandle)
        if (argument.noteId != -1) {
            viewModelScope.launch {
                noteUseCases.getNote(argument.noteId)?.also { note ->
                    currentNoteId = note.id
                    _noteTitle.value = note.title
                    _noteContent.value = note.content
                }
            }
        }
    }

    private val _noteTitle = mutableStateOf("")
    val noteTitle: State<String> = _noteTitle

    private val _noteContent = mutableStateOf("")
    val noteContent: State<String> = _noteContent

    private var currentNoteId: Int? = null


    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = event.value
            }

            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = event.value
            }
            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(
                        Note(
                            title = noteTitle.value,
                            content = noteContent.value,
                            timestamp = System.currentTimeMillis(),
                        ).apply {
                            currentNoteId?.let {
                                id = it
                            }
                        }
                    )

                }
            }
        }
    }
}