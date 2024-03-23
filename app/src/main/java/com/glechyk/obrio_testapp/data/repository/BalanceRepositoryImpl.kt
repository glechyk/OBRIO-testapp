package com.glechyk.obrio_testapp.data.repository

import com.glechyk.obrio_testapp.data.db.dao.BalanceDao
import com.glechyk.obrio_testapp.data.mapper.toDomain
import com.glechyk.obrio_testapp.data.mapper.toEntity
import com.glechyk.obrio_testapp.domain.model.BalanceDomain
import com.glechyk.obrio_testapp.domain.repository.BalanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BalanceRepositoryImpl @Inject constructor(
    private val balanceDao: BalanceDao,
) : BalanceRepository {

    override suspend fun upsertBalance(balanceDomain: BalanceDomain) {
        balanceDao.upsertBalanceEntity(balanceDomain.toEntity())
    }

    override suspend fun getBalance(): Flow<BalanceDomain> =
        balanceDao.getBalanceEntity().map { it?.toDomain() ?: BalanceDomain() }

}