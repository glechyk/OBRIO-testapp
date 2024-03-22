package com.glechyk.obrio_testapp.domain.usecase

import com.glechyk.obrio_testapp.domain.model.BalanceDomain
import com.glechyk.obrio_testapp.domain.repository.BalanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetBalanceUseCase {

    suspend operator fun invoke(): Flow<BalanceDomain>
}

class GetBalanceUseCaseImpl @Inject constructor(
    private val balanceRepository: BalanceRepository,
) : GetBalanceUseCase {

    override suspend fun invoke(): Flow<BalanceDomain> =
        balanceRepository.getBalance()
}
