package com.automacorp.service

import com.automacorp.model.HeaterDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HeatersApiService {
    @GET("heaters/custom/{roomName}")
    fun findHeatersByRoomName(@Path("roomName") roomName: String): Call<List<HeaterDto>>
}