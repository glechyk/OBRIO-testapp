package com.glechyk.obrio_testapp.data.mapper

import com.glechyk.obrio_testapp.data.db.entity.BalanceEntity
import com.glechyk.obrio_testapp.domain.model.BalanceDomain

fun BalanceEntity.toDomain() = BalanceDomain(
    amount = amount,
)

fun BalanceDomain.toEntity() = BalanceEntity(
    amount = amount,
)