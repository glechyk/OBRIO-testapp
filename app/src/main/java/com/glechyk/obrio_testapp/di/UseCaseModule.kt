package com.glechyk.obrio_testapp.di

import com.glechyk.obrio_testapp.domain.usecase.AddTransactionUseCase
import com.glechyk.obrio_testapp.domain.usecase.AddTransactionUseCaseImpl
import com.glechyk.obrio_testapp.domain.usecase.GetBalanceUseCase
import com.glechyk.obrio_testapp.domain.usecase.GetBalanceUseCaseImpl
import com.glechyk.obrio_testapp.domain.usecase.GetCurrentPriceUseCase
import com.glechyk.obrio_testapp.domain.usecase.GetCurrentPriceUseCaseImpl
import com.glechyk.obrio_testapp.domain.usecase.GetTransactionsListUseCase
import com.glechyk.obrio_testapp.domain.usecase.GetTransactionsListUseCaseImpl
import com.glechyk.obrio_testapp.domain.usecase.UpdateCurrentPriceUseCase
import com.glechyk.obrio_testapp.domain.usecase.UpdateCurrentPriceUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    @Singleton
    fun bindGetCurrentPriceUseCase(
        getCurrentPriceUseCaseImpl: GetCurrentPriceUseCaseImpl
    ): GetCurrentPriceUseCase

    @Binds
    @Singleton
    fun bindUpdateCurrentPriceUseCase(
        updateCurrentPriceUseCaseImpl: UpdateCurrentPriceUseCaseImpl
    ): UpdateCurrentPriceUseCase

    @Binds
    @Singleton
    fun bindGetBalanceUseCase(
        getBalanceUseCaseImpl: GetBalanceUseCaseImpl
    ): GetBalanceUseCase

    @Binds
    @Singleton
    fun bindGetTransactionsListUseCase(
        getTransactionsListUseCaseImpl: GetTransactionsListUseCaseImpl
    ): GetTransactionsListUseCase

    @Binds
    @Singleton
    fun bindInsertTransactionUseCase(
        insertTransactionUseCaseImpl: AddTransactionUseCaseImpl
    ): AddTransactionUseCase
}