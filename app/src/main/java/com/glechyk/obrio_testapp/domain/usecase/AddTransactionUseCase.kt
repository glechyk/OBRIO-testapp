package com.glechyk.obrio_testapp.domain.usecase

import com.glechyk.obrio_testapp.domain.model.BalanceDomain
import com.glechyk.obrio_testapp.domain.model.Category
import com.glechyk.obrio_testapp.domain.model.TransactionDomain
import com.glechyk.obrio_testapp.domain.repository.BalanceRepository
import com.glechyk.obrio_testapp.domain.repository.TransactionRepository
import javax.inject.Inject

interface AddTransactionUseCase {

    suspend operator fun invoke(
        balanceAmount: Double,
        transactionAmount: Double,
        category: Category?,
    )
}

class AddTransactionUseCaseImpl @Inject constructor(
    private val balanceRepository: BalanceRepository,
    private val transactionRepository: TransactionRepository,
) : AddTransactionUseCase {

    override suspend fun invoke(
        balanceAmount: Double,
        transactionAmount: Double,
        category: Category?,
    ) {
        val updatedBalance = BalanceDomain(
            if (category == null) {
                balanceAmount + transactionAmount
            } else {
                balanceAmount - transactionAmount
            }
        )
        val transaction = if (category == null) {
            TransactionDomain.Increase(
                amount = transactionAmount,
                time = System.currentTimeMillis()
            )
        } else {
            TransactionDomain.Decrease(
                amount = transactionAmount,
                category = category,
                time = System.currentTimeMillis()
            )
        }

        balanceRepository.upsertBalance(updatedBalance)
        transactionRepository.insertTransaction(transaction)
    }
}
