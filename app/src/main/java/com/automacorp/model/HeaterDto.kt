package com.automacorp.model

data class HeaterDto (
    val id: Long,
    val name: String,
    val roomId: Long,
    val heaterStatus: HeaterStatus
)