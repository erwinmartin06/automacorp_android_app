package com.automacorp.model

data class RoomDto(
    val id: Long,
    val name: String,
    val currentTemp: Double,
    val targetTemp: Double?,
    val floor: Int,
    val buildingId: Long,
    val windows: List<WindowDto>,
    val heaters: List<HeaterDto>
)