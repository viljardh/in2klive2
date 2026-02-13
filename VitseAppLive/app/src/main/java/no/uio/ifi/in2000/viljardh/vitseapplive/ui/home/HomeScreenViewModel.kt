package no.uio.ifi.in2000.viljardh.vitseapplive.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.viljardh.vitseapplive.data.alpacas.AlpacaPartiesRepository
import no.uio.ifi.in2000.viljardh.vitseapplive.model.alpacas.PartyInfo

data class AlpacaPartiesUiState(
    val parties: List<PartyInfo> = emptyList()
)

class HomeScreenViewModel: ViewModel() {
    private val alpacaPartiesRepository: AlpacaPartiesRepository =
        AlpacaPartiesRepository.AlpacaPartiesRepositoryImpl()
    private val _alpacaPartiesUiState = MutableStateFlow(AlpacaPartiesUiState())
    val alpacaPartiesUiState = _alpacaPartiesUiState.asStateFlow()

    init {
        loadParties()
    }

    private fun loadParties() {
        viewModelScope.launch(Dispatchers.IO) {
            _alpacaPartiesUiState.update { cur ->
                val parties = alpacaPartiesRepository.getPartyInfoList()
                cur.copy(parties = parties)
            }
        }
    }
}