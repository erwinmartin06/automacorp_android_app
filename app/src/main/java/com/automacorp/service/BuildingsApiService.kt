package com.automacorp.service

import com.automacorp.model.BuildingDto
import retrofit2.Call
import retrofit2.http.GET

interface BuildingsApiService {
    @GET("buildings")
    fun findAll(): Call<List<BuildingDto>>
}