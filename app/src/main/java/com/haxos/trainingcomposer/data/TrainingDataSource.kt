package com.haxos.trainingcomposer.data

import com.haxos.trainingcomposer.data.entity.Training

interface TrainingDataSource {
    suspend fun getTraining(id: Int): Training
}