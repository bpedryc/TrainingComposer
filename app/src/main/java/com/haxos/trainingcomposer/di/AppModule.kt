package com.haxos.trainingcomposer.di

import com.haxos.trainingcomposer.data.FakeTrainingDataSource
import com.haxos.trainingcomposer.data.TrainingDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindTrainingDataSource(
        fakeTrainingDataSource: FakeTrainingDataSource
    ): TrainingDataSource
}