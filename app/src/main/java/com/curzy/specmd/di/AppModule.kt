package com.curzy.specmd.di

import com.curzy.specmd.data.repository.SpecRepositoryImpl
import com.curzy.specmd.domain.repository.SpecRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindSpecRepository(impl: SpecRepositoryImpl): SpecRepository
}
