package com.haxos.trainingcomposer

import com.google.common.truth.Truth.assertThat
import com.haxos.trainingcomposer.data.TrainingDataSource
import com.haxos.trainingcomposer.data.entity.Exercise
import com.haxos.trainingcomposer.data.entity.ExerciseType
import com.haxos.trainingcomposer.data.entity.Training
import com.haxos.trainingcomposer.ui.trainingcreation.TrainingCreationViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

// https://stackoverflow.com/a/61667162

@OptIn(ExperimentalCoroutinesApi::class)
class TrainingCreationViewModelTest {
    private val mockDataSource: TrainingDataSource = mockk()
    private lateinit var viewModel: TrainingCreationViewModel
    private val sampleTraining = Training(
        id = 0,
        name = "Ultimate training",
        exercises = listOf(
            Exercise(id = 0, type = ExerciseType.PULLUPS, reps = 10, sets = 2),
            Exercise(id = 1, type = ExerciseType.SQUATS, reps = 15, sets = 3)
        )
    )

    @get:Rule
    val coroutineRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @Before
    fun setUp() {
        viewModel = TrainingCreationViewModel(mockDataSource)
        coEvery { mockDataSource.getTraining(any()) } returns sampleTraining
    }

    @Test
    fun `initially there are no exercises in viewModel`() = runTest {
        assertThat(viewModel.training.first().exercises).isEmpty()
    }

    @Test
    fun `after training loaded there are exercises in viewModel`() = runTest {
        viewModel.loadTraining()
        assertThat(viewModel.training.first().exercises).hasSize(2)
    }

    @Test
    fun `when pullups exercise is deleted squats exercise remains only`() = runTest {
        viewModel.loadTraining()
        viewModel.onDeleteExercise(0)

        val training = viewModel.training.first()
        assertThat(training.exercises).hasSize(1)
        assertThat(training.exercises.first().type).isEqualTo(ExerciseType.SQUATS)
    }
}
