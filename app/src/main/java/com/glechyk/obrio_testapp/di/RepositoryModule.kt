package com.glechyk.obrio_testapp.di

import com.glechyk.obrio_testapp.data.repository.BalanceRepositoryImpl
import com.glechyk.obrio_testapp.data.repository.CurrentPriceRepositoryImpl
import com.glechyk.obrio_testapp.data.repository.TransactionRepositoryImpl
import com.glechyk.obrio_testapp.domain.repository.BalanceRepository
import com.glechyk.obrio_testapp.domain.repository.CurrentPriceRepository
import com.glechyk.obrio_testapp.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindBalanceRepository(
        balanceRepositoryImpl: BalanceRepositoryImpl,
    ): BalanceRepository

    @Binds
    @Singleton
    fun bindCurrentPriceRepository(
        currentPriceRepositoryImpl: CurrentPriceRepositoryImpl,
    ): CurrentPriceRepository

    @Binds
    @Singleton
    fun bindTransactionRepository(
        transactionRepositoryImpl: TransactionRepositoryImpl,
    ): TransactionRepository
}