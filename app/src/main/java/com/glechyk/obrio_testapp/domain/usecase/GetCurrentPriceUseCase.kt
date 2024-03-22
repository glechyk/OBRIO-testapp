package com.glechyk.obrio_testapp.domain.usecase

import com.glechyk.obrio_testapp.domain.model.CurrentPriceDomain
import com.glechyk.obrio_testapp.domain.repository.CurrentPriceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCurrentPriceUseCase {

    suspend operator fun invoke(): Flow<CurrentPriceDomain>
}

class GetCurrentPriceUseCaseImpl @Inject constructor(
    private val currentPriceRepository: CurrentPriceRepository,
) : GetCurrentPriceUseCase {

    override suspend fun invoke(): Flow<CurrentPriceDomain> =
        currentPriceRepository.getCurrentPrice()
}
