package com.glechyk.obrio_testapp.domain.repository

import com.glechyk.obrio_testapp.domain.model.BalanceDomain
import kotlinx.coroutines.flow.Flow

interface BalanceRepository {

    suspend fun upsertBalance(balanceDomain: BalanceDomain)

    suspend fun getBalance(): Flow<BalanceDomain>
}