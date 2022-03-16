package com.haxos.trainingcomposer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun TrainingCreationScreen(
    viewModel: TrainingCreationViewModel = hiltViewModel()
) {
    val exercises by viewModel.exercises.collectAsState()
    LazyColumn {
        items(exercises) { exercise ->
            ExerciseEntry(
                exercise = exercise,
                onExerciseDelete = { viewModel.onDeleteExercise(it) }
            )
        }
    }
}

@Composable
fun ExerciseEntry(exercise: Exercise, onExerciseDelete: (id: Int) -> Unit) {
    Row {
        Icon(
            painter = rememberVectorPainter(Icons.Default.FitnessCenter),
            contentDescription = "Exercise icon"
        )
        Text(
            text = when (exercise.type) {
                ExerciseType.PUSHUPS -> stringResource(R.string.pushups)
                ExerciseType.PULLUPS -> stringResource(R.string.pullups)
                ExerciseType.DIPS -> stringResource(R.string.dips)
                ExerciseType.SQUATS -> stringResource(R.string.squats)
            }
        )
        Text(exercise.reps.toString())
        Text(exercise.sets.toString())
        IconButton(
            onClick = { onExerciseDelete(exercise.id) }
        ) {
            Icon(
                painter = rememberVectorPainter(Icons.Default.Delete),
                contentDescription = "Delete exercise"
            )
        }
    }
}
