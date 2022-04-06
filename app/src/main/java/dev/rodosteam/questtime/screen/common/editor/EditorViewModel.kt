package dev.rodosteam.questtime.screen.common.editor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "WIP Quest Editor"
    }
    val text: LiveData<String> = _text
}