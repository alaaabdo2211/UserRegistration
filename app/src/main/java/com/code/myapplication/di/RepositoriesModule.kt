package com.code.myapplication.di

import com.code.myapplication.data.local.AuthenticationRepository
import com.code.myapplication.data.remote.MostViewRepository
import com.code.myapplication.data.remote.MostViewRepositoryImpl
import com.code.myapplication.domain.repositories.AuthenticationRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {
    @Binds
    abstract fun bindAuthRepo(repository: AuthenticationRepoImpl): AuthenticationRepository
    @Binds
    abstract fun bindMostViewedRepo(repository: MostViewRepositoryImpl) : MostViewRepository
}