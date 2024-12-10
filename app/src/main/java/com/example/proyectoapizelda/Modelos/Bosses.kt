package com.example.proyectoapizelda.Modelos

data class BossesResponse(
    val success: Boolean,
    val count: Int,
    val data: List<Boss>
)



data class Boss(
    val name: String,
    val description: String,
    val appearances: List<String>,
    val gender: String? = null,
    val race: String? = null,
    val id: String
)

