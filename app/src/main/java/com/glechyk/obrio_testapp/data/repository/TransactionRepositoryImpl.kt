package com.glechyk.obrio_testapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.glechyk.obrio_testapp.data.db.dao.TransactionDao
import com.glechyk.obrio_testapp.data.db.entity.TransactionEntity
import com.glechyk.obrio_testapp.data.mapper.toDomain
import com.glechyk.obrio_testapp.data.mapper.toEntity
import com.glechyk.obrio_testapp.domain.model.TransactionDomain
import com.glechyk.obrio_testapp.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val pager: Pager<Int, TransactionEntity>,
) : TransactionRepository {

    override suspend fun insertTransaction(transactionDomain: TransactionDomain) {
        transactionDao.insertTransactionEntity(transactionDomain.toEntity())
    }

    override suspend fun getTransactionsList(): Flow<PagingData<TransactionDomain>> {
        return pager.flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}