package com.example.proyectoapizelda.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoapizelda.Modelos.Boss
import com.example.proyectoapizelda.implementacionAPI.ApiServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BossesViewModel(private val api: ApiServices) : ViewModel() {

    private val _bosses = MutableStateFlow<List<Boss>>(emptyList())
    val bosses: StateFlow<List<Boss>> get() = _bosses

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun fetchBosses(limit: Int = 20) {
        viewModelScope.launch {
            try {
                val response = api.getBosses(limit)

                if (response.isSuccessful) {
                    val bossResponse = response.body()

                    if (bossResponse != null && bossResponse.success) {
                        _bosses.value = bossResponse.data
                    } else {
                        _error.value = "Error: No se pudieron cargar los bosses"
                        Log.e("BossesViewModel", "Error en la respuesta del servidor")
                    }
                } else {
                    _error.value = "Error: Respuesta HTTP no exitosa"
                    Log.e("BossesViewModel", "Error HTTP: ${response.code()}")
                }
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
                Log.e("BossesViewModel", "Error al realizar la solicitud: ${e.message}", e)
            }
        }
    }

}