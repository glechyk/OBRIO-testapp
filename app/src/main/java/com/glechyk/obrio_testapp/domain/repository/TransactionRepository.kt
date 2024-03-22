package com.glechyk.obrio_testapp.domain.repository

import androidx.paging.PagingData
import com.glechyk.obrio_testapp.domain.model.TransactionDomain
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun insertTransaction(transactionDomain: TransactionDomain)

    suspend fun getTransactionsList(): Flow<PagingData<TransactionDomain>>
}