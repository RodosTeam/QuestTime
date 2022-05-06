package dev.rodosteam.questtime.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.rodosteam.questtime.App
import dev.rodosteam.questtime.screen.external.ExternalViewModel
import dev.rodosteam.questtime.screen.library.LibraryViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val app: App
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            ExternalViewModel::class.java -> {
                ExternalViewModel(app.questRepo)
            }
            LibraryViewModel::class.java -> {
                LibraryViewModel(app.questRepo)
            }
            else -> {
                throw IllegalArgumentException("Unknown view model class")
            }
        }
        return viewModel as T
    }

}