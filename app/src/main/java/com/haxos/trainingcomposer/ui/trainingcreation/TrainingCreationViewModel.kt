package com.haxos.trainingcomposer.ui.trainingcreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.trainingcomposer.data.TrainingDataSource
import com.haxos.trainingcomposer.data.entity.Training
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingCreationViewModel @Inject constructor(
    private val trainingDataSource: TrainingDataSource
) : ViewModel() {

    private val _training = MutableStateFlow(Training.placeholder())
    val training: StateFlow<Training> = _training

    fun loadTraining() = viewModelScope.launch {
        val training = trainingDataSource.getTraining(0)
        _training.emit(training)
    }

    fun onDeleteExercise(exerciseToDeleteId: Int) = viewModelScope.launch {
        _training.update {
            it.copy(
                exercises = it.exercises.filterNot { exercise ->
                    exercise.id == exerciseToDeleteId
                }
            )
        }
    }
}
