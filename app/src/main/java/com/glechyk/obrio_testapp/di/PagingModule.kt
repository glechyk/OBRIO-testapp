package com.glechyk.obrio_testapp.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.glechyk.obrio_testapp.data.db.dao.TransactionDao
import com.glechyk.obrio_testapp.data.db.entity.TransactionEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PagingModule {

    @Provides
    fun provideTransactionsPager(transactionDao: TransactionDao): Pager<Int, TransactionEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { transactionDao.getAllTransactions() },
        )
    }
}