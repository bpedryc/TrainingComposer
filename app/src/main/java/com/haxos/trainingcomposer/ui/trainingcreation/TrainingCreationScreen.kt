package com.haxos.trainingcomposer.ui.trainingcreation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haxos.trainingcomposer.R
import com.haxos.trainingcomposer.data.entity.Exercise
import com.haxos.trainingcomposer.data.entity.ExerciseType
import com.haxos.trainingcomposer.ui.theme.TrainingComposerTheme
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun TrainingCreationScreen(
    viewModel: TrainingCreationViewModel = hiltViewModel()
) {
    val trainingState by viewModel.training.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadTraining()
    }
    ExerciseTable(
        exercises = trainingState.exercises,
        onDeleteExercise = { viewModel.onDeleteExercise(it) }
    )
}

@Composable
fun ExerciseTable(
    exercises: List<Exercise>,
    onDeleteExercise: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.background(MaterialTheme.colors.surface)
    ) {
        item {
            Row(modifier = Modifier.padding(vertical = 10.dp)) {
                Spacer(modifier = Modifier.weight(1f))
                Text(modifier = Modifier.weight(2f), text = stringResource(R.string.exercise))
                Text(modifier = Modifier.weight(1f), text = stringResource(R.string.reps))
                Text(modifier = Modifier.weight(1f), text = stringResource(R.string.sets))
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        items(exercises) { exercise ->
            ExerciseEntry(
                exercise = exercise,
                onDeleteExercise = onDeleteExercise
            )
        }
    }
}

@Composable
fun ExerciseEntry(
    modifier: Modifier = Modifier,
    exercise: Exercise,
    onDeleteExercise: (id: Int) -> Unit
) {
    Row(
        modifier = modifier.background(MaterialTheme.colors.surface),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier.weight(1f),
            painter = rememberVectorPainter(Icons.Default.FitnessCenter),
            contentDescription = "Exercise icon",
            tint = MaterialTheme.colors.onSurface
        )
        Text(
            modifier = Modifier.weight(2f),
            text = when (exercise.type) {
                ExerciseType.PUSHUPS -> stringResource(R.string.pushups)
                ExerciseType.PULLUPS -> stringResource(R.string.pullups)
                ExerciseType.DIPS -> stringResource(R.string.dips)
                ExerciseType.SQUATS -> stringResource(R.string.squats)
            },
            color = MaterialTheme.colors.onSurface
        )
        Text(
            modifier = Modifier.weight(1f),
            text = exercise.reps.toString(),
            color = MaterialTheme.colors.onSurface
        )
        Text(
            modifier = Modifier.weight(1f),
            text = exercise.sets.toString(),
            color = MaterialTheme.colors.onSurface
        )
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { onDeleteExercise(exercise.id) },
        ) {
            Icon(
                painter = rememberVectorPainter(Icons.Default.Delete),
                contentDescription = "Delete exercise",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Preview
@Composable
fun ExerciseTablePreview() {
    TrainingComposerTheme {
        ExerciseTable(
            exercises = listOf(
                Exercise(id = 0, type = ExerciseType.PUSHUPS, reps = 5, sets = 4),
                Exercise(id = 1, type = ExerciseType.PULLUPS, reps = 4, sets = 3),
                Exercise(id = 2, type = ExerciseType.DIPS, reps = 6, sets = 4)
            ),
            onDeleteExercise = {}
        )
    }
}

@Preview
@Composable
fun ExerciseEntryLightPreview() {
    TrainingComposerTheme() {
        ExerciseEntry(
            modifier = Modifier.fillMaxWidth(),
            exercise = Exercise(id = 0, type = ExerciseType.PUSHUPS, reps = 20, sets = 4),
            onDeleteExercise = {}
        )
    }
}

@Preview
@Composable
fun ExerciseEntryDarkPreview() {
    TrainingComposerTheme(darkTheme = true) {
        ExerciseEntry(
            modifier = Modifier.fillMaxWidth(),
            exercise = Exercise(id = 0, type = ExerciseType.PUSHUPS, reps = 20, sets = 4),
            onDeleteExercise = {}
        )
    }
}
