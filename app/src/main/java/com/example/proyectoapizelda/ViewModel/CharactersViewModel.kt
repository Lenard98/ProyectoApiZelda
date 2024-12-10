package com.example.proyectoapizelda.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoapizelda.Modelos.Character
import com.example.proyectoapizelda.implementacionAPI.ApiServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(private val api: ApiServices) : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> get() = _characters

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun fetchCharacters(limit: Int = 20) {
        viewModelScope.launch {
            try {
                val response = api.getCharacters(limit)


                Log.d("CharacterViewModel", "API Response: $response")

                if (response.success) {
                    _characters.value = response.data
                } else {
                    _error.value = "Error: No se pudieron cargar los personajes"
                    Log.e("CharacterViewModel", "Error en la respuesta: ${response.success}")
                }
            } catch (e: Exception) {

                _error.value = "Error: ${e.message}"
                Log.e("CharacterViewModel", "Error al realizar la solicitud: ${e.message}", e)
            }
        }
    }
}
