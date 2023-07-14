package com.ash4rk.core.data.di

import com.ash4rk.core.data.repository.InfoRepository
import com.ash4rk.core.data.repository.InfoRepositoryImpl
import com.ash4rk.core.data.repository.MainRepository
import com.ash4rk.core.data.repository.MainRepositoryImpl
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
