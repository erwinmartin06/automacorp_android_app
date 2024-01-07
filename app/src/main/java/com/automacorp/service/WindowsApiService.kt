package com.automacorp.service

import com.automacorp.model.WindowDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WindowsApiService {
    @GET("windows/custom/{roomName}")
    fun findWindowsByRoomName(@Path("roomName") roomName: String): Call<List<WindowDto>>
}