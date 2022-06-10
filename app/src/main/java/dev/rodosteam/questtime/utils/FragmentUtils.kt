package dev.rodosteam.questtime.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.rodosteam.questtime.App
import dev.rodosteam.questtime.quest.database.Quest
import dev.rodosteam.questtime.quest.model.QuestContent
import dev.rodosteam.questtime.quest.model.Walkthrough
import dev.rodosteam.questtime.quest.repo.content.QuestContentRepoJson
import dev.rodosteam.questtime.screen.external.ExternalViewModel
import dev.rodosteam.questtime.screen.library.LibraryViewModel
import dev.rodosteam.questtime.screen.questContent.QuestContentViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val app: App,
    private val quest: Quest? = null
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            ExternalViewModel::class.java -> {
                ExternalViewModel(app.questRepo)
            }
            LibraryViewModel::class.java -> {
                LibraryViewModel(app.questRepo)
            }
            QuestContentViewModel::class.java -> {
                var content : QuestContent? = null
                quest?.let {
                    content = QuestContentRepoJson.readQuest(quest.contentJson)
                }
                QuestContentViewModel(Walkthrough(content!!))
            }
            else -> {
                throw IllegalArgumentException("Unknown view model class")
            }
        }
        return viewModel as T
    }

}