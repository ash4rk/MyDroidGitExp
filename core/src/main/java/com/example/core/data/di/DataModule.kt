package com.example.core.data.di

import android.annotation.SuppressLint
import com.example.core.data.repository.MainRepository
import com.example.core.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindsMainRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ): MainRepository

}
*/



@Module()
@InstallIn(SingletonComponent::class)
object DataModule {

/*
    @SuppressLint("VisibleForTests")
    @Provides
    @Singleton
    fun providesRepo(): MainRepository {
        return MainRepositoryImpl()
    }
*/

    @Module
    @InstallIn(SingletonComponent::class)
    interface Test{
        @Binds
        fun bindsMainRepository(MainRepository: MainRepositoryImpl): MainRepository
    }

}
