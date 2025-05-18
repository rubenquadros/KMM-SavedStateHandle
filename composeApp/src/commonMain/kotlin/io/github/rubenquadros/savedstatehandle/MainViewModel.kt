package io.github.rubenquadros.savedstatehandle

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.serialization.saved
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

class MainViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

//    private var _savedState by savedStateHandle.saved(
//        key = "SAVED_STATE",
//        init = { UiState() }
//    )

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    fun increment() {
        _uiState.update { state ->
            val counter = state.counter
            state.copy(counter = counter+1)
            //_savedState = state.copy(counter = counter+1)
            //_savedState
        }
    }

    fun decrement() {
        _uiState.update { state ->
            val counter = state.counter
            state.copy(counter = counter-1)
            //_savedState = state.copy(counter = counter-1)
            //_savedState
        }
    }

    companion object {
        val FACTORY = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return MainViewModel(
                    savedStateHandle = extras.createSavedStateHandle()
                ) as T
            }
        }
    }
}

@Serializable
data class UiState(
    val counter: Int = 0
)