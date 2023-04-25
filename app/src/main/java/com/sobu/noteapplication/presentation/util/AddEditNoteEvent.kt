package com.sobu.noteapplication.presentation.util

sealed class AddEditNoteEvent {
    data class EnteredTitle(val value: String) : AddEditNoteEvent()
    data class EnteredContent(val value: String) : AddEditNoteEvent()

    object SaveNote : AddEditNoteEvent()
}