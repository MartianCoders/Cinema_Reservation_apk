package com.example.rezervarecinemaiss.DInjection

import android.app.Application
import android.content.Context
import com.example.rezervarecinemaiss.Data.MovieDAO
import com.example.rezervarecinemaiss.Data.ReservationDAO
import com.example.rezervarecinemaiss.Data.UserDAO
import com.example.rezervarecinemaiss.Database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    @ViewModelScoped
    fun getAppDatabase(context: Application): AppDatabase {
        return AppDatabase.getAppDatabase(context)
    }

    @Provides
    @ViewModelScoped
    fun getUserDAO(appDatabase: AppDatabase): UserDAO {
        return appDatabase.getUserDAO()
    }

    @Provides
    @ViewModelScoped
    fun getMovieDAO(appDatabase: AppDatabase): MovieDAO {
        return appDatabase.getMovieDAO()
    }

    @Provides
    @ViewModelScoped
    fun getReservationDAO(appDatabase: AppDatabase): ReservationDAO {
        return appDatabase.getReservationDAO()
    }
}