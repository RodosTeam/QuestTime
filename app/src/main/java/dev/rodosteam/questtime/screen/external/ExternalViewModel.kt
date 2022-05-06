package dev.rodosteam.questtime.screen.external

import androidx.lifecycle.*
import dev.rodosteam.questtime.quest.database.Quest
import dev.rodosteam.questtime.quest.database.QuestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExternalViewModel(private val repository: QuestRepository) : ViewModel() {

    var loaded = listOf<Quest>()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            loaded = repository.readAllData()
        }
    }

    fun addQuest(quest: Quest) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addQuest(quest)
        }
    }
}