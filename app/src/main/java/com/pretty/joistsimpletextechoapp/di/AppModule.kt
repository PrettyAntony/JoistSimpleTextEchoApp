package com.pretty.joistsimpletextechoapp.di

import com.pretty.joistsimpletextechoapp.data.local.EchoDataSource
import com.pretty.joistsimpletextechoapp.data.repository.EchoRepository
import com.pretty.joistsimpletextechoapp.data.repository.EchoRepositoryImpl
import com.pretty.joistsimpletextechoapp.domain.usecase.ValidateTextUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideEchoDataSource(): EchoDataSource = EchoDataSource()

    @Provides
    fun provideEchoRepository(dataSource: EchoDataSource): EchoRepository =
        EchoRepositoryImpl(dataSource)

    @Provides
    fun provideValidateAndEchoTextUseCase(repository: EchoRepository): ValidateTextUseCase =
        ValidateTextUseCase(repository)
}