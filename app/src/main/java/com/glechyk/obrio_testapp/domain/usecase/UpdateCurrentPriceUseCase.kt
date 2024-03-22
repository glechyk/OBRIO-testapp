package com.glechyk.obrio_testapp.domain.usecase

import com.glechyk.obrio_testapp.domain.repository.CurrentPriceRepository
import javax.inject.Inject

interface UpdateCurrentPriceUseCase {

    suspend operator fun invoke()
}

class UpdateCurrentPriceUseCaseImpl @Inject constructor(
    private val currentPriceRepository: CurrentPriceRepository,
) : UpdateCurrentPriceUseCase {

    override suspend fun invoke() = currentPriceRepository.updateCurrentPrice()
}
