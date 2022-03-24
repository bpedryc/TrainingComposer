package com.haxos.trainingcomposer.ui.trainingcreation

import androidx.lifecycle.ViewModel
import com.haxos.trainingcomposer.data.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TrainingCreationViewModel @Inject constructor() : ViewModel() {

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises

    fun onDeleteExercise(exerciseId: Int) {
        _exercises.value = _exercises.value.filter { it.id != exerciseId }
    }

}
