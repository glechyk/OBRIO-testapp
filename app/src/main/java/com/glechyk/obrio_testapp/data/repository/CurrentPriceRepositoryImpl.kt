package com.glechyk.obrio_testapp.data.repository

import com.glechyk.obrio_testapp.data.db.dao.CurrentPriceDao
import com.glechyk.obrio_testapp.data.mapper.toDomain
import com.glechyk.obrio_testapp.data.mapper.toEntity
import com.glechyk.obrio_testapp.data.network.BitcoinsApi
import com.glechyk.obrio_testapp.domain.model.CurrentPriceDomain
import com.glechyk.obrio_testapp.domain.repository.CurrentPriceRepository
import com.glechyk.obrio_testapp.utils.NetworkException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrentPriceRepositoryImpl @Inject constructor(
    private val bitcoinsApi: BitcoinsApi,
    private val currentPriceDao: CurrentPriceDao,
) : CurrentPriceRepository {
    override suspend fun updateCurrentPrice() {
        val response = bitcoinsApi.getCurrentPrice()

        if (response.isSuccessful) {
            response.body()?.let {
                currentPriceDao.upsertCurrentPrice(it.toEntity())
            }
        } else {
            throw NetworkException(response.code())
        }
    }

    override suspend fun getCurrentPrice(): Flow<CurrentPriceDomain> =
        currentPriceDao.getCurrentPriceEntity().map { it?.toDomain() ?: CurrentPriceDomain() }

}