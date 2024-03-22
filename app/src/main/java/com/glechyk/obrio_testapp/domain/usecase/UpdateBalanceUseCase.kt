package com.glechyk.obrio_testapp.domain.usecase

import com.glechyk.obrio_testapp.domain.model.BalanceDomain
import com.glechyk.obrio_testapp.domain.repository.BalanceRepository
import javax.inject.Inject

interface UpdateBalanceUseCase {

    suspend operator fun invoke(balanceDomain: BalanceDomain)
}

class UpdateBalanceUseCaseImpl @Inject constructor(
    private val balanceRepository: BalanceRepository,
) : UpdateBalanceUseCase {

    override suspend fun invoke(balanceDomain: BalanceDomain) =
        balanceRepository.upsertBalance(balanceDomain)
}
