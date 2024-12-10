package com.example.proyectoapizelda.implementacionAPI

import com.example.proyectoapizelda.Modelos.BossesResponse
import com.example.proyectoapizelda.Modelos.CharacterResponse
import com.example.proyectoapizelda.Modelos.DungeonsResponse
import com.example.proyectoapizelda.Modelos.GameResponse
import com.example.proyectoapizelda.Modelos.MonsterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("games")
    suspend fun getGames(
        @Query("limit") limit: Int = 20
    ): GameResponse

    @GET("monsters")
    suspend fun getMonsters(
        @Query("limit") limit: Int = 20
    ): MonsterResponse

    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int = 20
    ): CharacterResponse
    @GET("bosses")
    suspend fun getBosses(
        @Query("limit") limit: Int
    ): Response<BossesResponse>

    @GET("dungeons")
    suspend fun getDungeons(
        @Query("limit") limit: Int = 20
    ): DungeonsResponse

}

