package com.haxos.trainingcomposer.data.entity

data class Training(
    val id: Int?,
    val name: String,
    val exercises: List<Exercise>
) {
    companion object {
        fun placeholder() = Training(id = null, name = "New Training", exercises = emptyList())
    }
}