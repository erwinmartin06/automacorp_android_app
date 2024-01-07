package com.automacorp.model

data class BuildingDto (
    val id: Long,
    val name: String,
    val outsideTemperature: Double,
    val rooms: List<RoomDto>
)