package com.example.core.data.di

import com.example.core.data.repository.InfoRepository
import com.example.core.data.repository.InfoRepositoryImpl
import com.example.core.data.repository.MainRepository
import com.example.core.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module()
@InstallIn(SingletonComponent::class)
object DataModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface Test{
        @Binds
        fun bindsMainRepository(MainRepository: MainRepositoryImpl): MainRepository

        @Binds
        fun bindsInfoRepository(InfoRepository: InfoRepositoryImpl): InfoRepository
    }

}
