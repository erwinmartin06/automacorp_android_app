package com.automacorp.model

data class RoomCommandDto(
    val name: String,
    val currentTempId: Long,
    val targetTemp: Double?,
    val floor: Int,
    val buildingId: Long
)