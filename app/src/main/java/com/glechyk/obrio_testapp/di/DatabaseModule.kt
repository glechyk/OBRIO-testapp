package com.glechyk.obrio_testapp.di

import android.content.Context
import androidx.room.Room
import com.glechyk.obrio_testapp.data.db.BitcoinsDatabase
import com.glechyk.obrio_testapp.data.db.dao.BalanceDao
import com.glechyk.obrio_testapp.data.db.dao.CurrentPriceDao
import com.glechyk.obrio_testapp.data.db.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideBitcoinsDatabase(@ApplicationContext context: Context): BitcoinsDatabase =
        Room.databaseBuilder(
            context,
            BitcoinsDatabase::class.java,
            "bitcoins.db"
        ).build()

    @Provides
    fun provideBalanceDao(database: BitcoinsDatabase): BalanceDao =
        database.balanceDao

    @Provides
    fun provideCurrentPriceDao(database: BitcoinsDatabase): CurrentPriceDao =
        database.currentPriceDao

    @Provides
    fun provideTransactionDao(database: BitcoinsDatabase): TransactionDao =
        database.transactionDao
}