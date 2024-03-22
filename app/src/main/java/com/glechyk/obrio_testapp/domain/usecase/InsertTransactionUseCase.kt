package com.glechyk.obrio_testapp.domain.usecase

import com.glechyk.obrio_testapp.domain.model.TransactionDomain
import com.glechyk.obrio_testapp.domain.repository.TransactionRepository
import javax.inject.Inject

interface InsertTransactionUseCase {

    suspend operator fun invoke(transactionDomain: TransactionDomain)
}

class InsertTransactionUseCaseImpl @Inject constructor(
    private val transactionRepository: TransactionRepository,
) : InsertTransactionUseCase {

    override suspend fun invoke(transactionDomain: TransactionDomain) =
        transactionRepository.insertTransaction(transactionDomain)
}
