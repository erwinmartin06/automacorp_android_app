package com.automacorp.service

import com.automacorp.model.WindowCommandDto
import com.automacorp.model.WindowDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WindowsApiService {
    @GET("windows")
    fun findAll(): Call<List<WindowDto>>

    @GET("windows/custom/{roomName}")
    fun findWindowsByRoomName(@Path("roomName") roomName: String): Call<List<WindowDto>>

    @GET("windows/{id}")
    fun findById(@Path("id") id: Long): Call<WindowDto>

    @PUT("windows/{id}")
    fun updateWindow(@Path("id") id: Long, @Body room: WindowCommandDto): Call<WindowDto>

    @POST
    fun createWindow(@Body room: WindowCommandDto): Call<WindowDto>

    @DELETE("windows/{id}")
    fun deleteWindowById(@Path("id") id: Long): Call<Void>
}