package com.glechyk.obrio_testapp.domain.usecase

import androidx.paging.PagingData
import com.glechyk.obrio_testapp.domain.model.TransactionDomain
import com.glechyk.obrio_testapp.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTransactionsListUseCase {

    suspend operator fun invoke(): Flow<PagingData<TransactionDomain>>
}

class GetTransactionsListUseCaseImpl @Inject constructor(
    private val transactionRepository: TransactionRepository,
) : GetTransactionsListUseCase {

    override suspend fun invoke(): Flow<PagingData<TransactionDomain>> =
        transactionRepository.getTransactionsList()
}
