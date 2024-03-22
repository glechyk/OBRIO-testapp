package com.glechyk.obrio_testapp.domain.repository

import com.glechyk.obrio_testapp.domain.model.CurrentPriceDomain
import kotlinx.coroutines.flow.Flow

interface CurrentPriceRepository {

    suspend fun updateCurrentPrice()

    suspend fun getCurrentPrice(): Flow<CurrentPriceDomain>
}