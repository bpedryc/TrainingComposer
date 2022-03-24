package com.haxos.trainingcomposer.data.entity

data class Exercise(
    val id: Int,
    val type: ExerciseType,
    val reps: Int,
    val sets: Int
)
