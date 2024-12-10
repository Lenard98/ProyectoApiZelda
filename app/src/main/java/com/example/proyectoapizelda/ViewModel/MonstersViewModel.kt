package com.example.proyectoapizelda.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoapizelda.Modelos.Monster
import com.example.proyectoapizelda.implementacionAPI.ApiServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MonsterViewModel(private val api: ApiServices) : ViewModel() {

    private val _monsters = MutableStateFlow<List<Monster>>(emptyList())
    val monsters: StateFlow<List<Monster>> get() = _monsters

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun fetchMonsters(limit: Int = 20) {
        viewModelScope.launch {
            try {
                val response = api.getMonsters(limit)
                if (response.success) {
                    _monsters.value = response.data
                } else {
                    _error.value = "Error: No se pudieron cargar los monstruos"
                }
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            }
        }
    }
}
