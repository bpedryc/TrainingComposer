package com.haxos.trainingcomposer.data

import com.haxos.trainingcomposer.data.entity.Exercise
import com.haxos.trainingcomposer.data.entity.ExerciseType
import com.haxos.trainingcomposer.data.entity.Training
import javax.inject.Inject

class FakeTrainingDataSource @Inject constructor() : TrainingDataSource {
    override fun getTraining(id: Int): Training {
        return Training(
            id = null,
            name = "Monday workout",
            exercises = listOf(
                Exercise(id = 0, type = ExerciseType.PUSHUPS, 4, 4),
                Exercise(id = 1, type = ExerciseType.PULLUPS, 8, 3),
                Exercise(id = 2, type = ExerciseType.SQUATS, 15, 4)
            )
        )
    }

}