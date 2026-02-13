package no.uio.ifi.in2000.viljardh.vitseapplive.ui.jokes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.viljardh.vitseapplive.data.jokes.JokesRepository
import no.uio.ifi.in2000.viljardh.vitseapplive.model.jokes.Joke

data class JokesUiState(
    val jokes: List<Joke> = emptyList()

)

class JokesViewModel: ViewModel() {
    private val jokesRepository: JokesRepository =
        JokesRepository.JokesRepositoryImpl()

    private val _jokesUiState = MutableStateFlow(JokesUiState())
    val jokesUiState = _jokesUiState.asStateFlow()

    private fun loadJokes() {
        viewModelScope.launch(Dispatchers.IO) {
            _jokesUiState.update {currentJoke ->
                val jokes = jokesRepository.getJokesList()
                currentJoke.copy(jokes = jokes)
            }
        }
    }

    init {
        loadJokes()
    }

}