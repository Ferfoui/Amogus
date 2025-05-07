package fr.ferfoui.amogus.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.ferfoui.amogus.data.random.RandomRepository
import fr.ferfoui.amogus.ui.mainscreen.RandomViewModel

class RandomViewModelFactory(
    private val randomRepository: RandomRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RandomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RandomViewModel(randomRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}