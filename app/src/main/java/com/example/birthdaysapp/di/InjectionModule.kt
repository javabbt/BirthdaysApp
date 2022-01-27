package com.example.birthdaysapp.di

import com.example.birthdaysapp.data.network.Api
import com.example.birthdaysapp.data.repositories.Repository
import com.example.birthdaysapp.ui.adapters.BirthdaysAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InjectionModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Api {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api:Api): Repository {
        return Repository(api)
    }

    @Provides
    @Singleton
    fun provideBirthdayAdapter(): BirthdaysAdapter {
        return BirthdaysAdapter()
    }

}