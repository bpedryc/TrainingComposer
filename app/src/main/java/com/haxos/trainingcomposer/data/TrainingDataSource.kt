package com.haxos.trainingcomposer.data

import com.haxos.trainingcomposer.data.entity.Training

interface TrainingDataSource {
    fun getTraining(id: Int): Training
}