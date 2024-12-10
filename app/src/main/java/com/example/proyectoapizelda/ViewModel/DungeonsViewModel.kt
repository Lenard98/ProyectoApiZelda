package com.example.proyectoapizelda.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoapizelda.Modelos.Dungeon
import com.example.proyectoapizelda.implementacionAPI.ApiServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DungeonViewModel(private val api: ApiServices) : ViewModel() {

    private val _dungeons = MutableStateFlow<List<Dungeon>>(emptyList())
    val dungeons: StateFlow<List<Dungeon>> get() = _dungeons

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun fetchDungeons(limit: Int = 20) {
        viewModelScope.launch {
            try {
                val response = api.getDungeons(limit)

                Log.d("DungeonViewModel", "API Response: $response")

                if (response.success) {
                    _dungeons.value = response.data
                } else {
                    _error.value = "Error: No se pudieron cargar los dungeons"
                    Log.e("DungeonViewModel", "Error en la respuesta: ${response.success}")
                }
            } catch (e: Exception) {

                _error.value = "Error: ${e.message}"
                Log.e("DungeonViewModel", "Error al realizar la solicitud: ${e.message}", e)
            }
        }
    }
}