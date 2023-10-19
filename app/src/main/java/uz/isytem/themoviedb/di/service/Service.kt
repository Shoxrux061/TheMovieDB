package uz.isytem.themoviedb.di.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.isytem.themoviedb.data.network.MovieService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Service {

    @[Provides Singleton]
    fun provideHomeService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}