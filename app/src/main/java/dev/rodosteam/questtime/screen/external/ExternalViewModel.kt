package dev.rodosteam.questtime.screen.external

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExternalViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is external Fragment"
    }
    val text: LiveData<String> = _text
}