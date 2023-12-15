package com.aminovic.lenador.di

import android.app.Application
import androidx.room.Room
import com.aminovic.lenador.data.local.PosDao
import com.aminovic.lenador.data.local.PosDatabase
import com.aminovic.lenador.data.repository.PosRepositoryImpl
import com.aminovic.lenador.domain.repository.PosRepository
import com.aminovic.lenador.domain.use_cases.InsertOrderUseCase
import com.aminovic.lenador.domain.use_cases.LoadProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePosDatabase(app: Application): PosDatabase {
        return Room.databaseBuilder(
            app,
            PosDatabase::class.java,
            "pos_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providePosDao(database: PosDatabase): PosDao {
        return database.posDao
    }

    @Provides
    @Singleton
    fun providePosRepository(dao: PosDao): PosRepository {
        return PosRepositoryImpl(dao = dao)
    }

    // use cases
    @Provides
    @Singleton
    fun provideInsertOrderUseCase(repository: PosRepository): InsertOrderUseCase {
        return InsertOrderUseCase(repository = repository)
    }

    @Provides
    @Singleton
    fun provideLoadProductsUseCase(repository: PosRepository): LoadProductsUseCase {
        return LoadProductsUseCase(repository = repository)
    }
}