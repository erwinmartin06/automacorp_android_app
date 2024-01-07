package com.automacorp.model

data class RoomCommandDto(
    val name: String,
    val currentTemp: Double,
    val targetTemp: Double?,
    val floor: Int,
    val buildingId: Long
)