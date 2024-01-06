package com.automacorp.model

data class WindowDto(
    val id: Long,
    val name: String,
    val windowStatus: WindowStatus,
    val roomId: Long
)