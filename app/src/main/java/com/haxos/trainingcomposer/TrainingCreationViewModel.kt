package com.haxos.trainingcomposer

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TrainingCreationViewModel {

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises

    fun onDeleteExercise(exerciseId: Int) {
        _exercises.value = _exercises.value.filter { it.id != exerciseId }
    }

}
