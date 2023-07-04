package com.example.core.database.di

import android.app.Application
import androidx.room.Room
import com.example.core.database.SearchUserDao
import com.example.core.database.UserDao
import com.example.core.database.UserDatabase
import com.example.core.database.UserInfoDao
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
    ): UserDatabase {
        return Room
            .databaseBuilder(application, UserDatabase::class.java, "GitHub.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: UserDatabase): UserDao {
        return appDatabase.userDao()
    }

  @Provides
    @Singleton
    fun provideSearchUserDao(appDatabase: UserDatabase): SearchUserDao {
        return appDatabase.searchUserDao()
    }

    @Provides
    @Singleton
    fun provideUserInfoDao(appDatabase: UserDatabase): UserInfoDao {
        return appDatabase.userInfoDao()
    }
}
