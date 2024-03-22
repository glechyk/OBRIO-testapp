package com.glechyk.obrio_testapp.data.mapper

import com.glechyk.obrio_testapp.data.db.entity.CurrentPriceEntity
import com.glechyk.obrio_testapp.data.network.model.response.CurrentPriceData
import com.glechyk.obrio_testapp.domain.model.CurrentPriceDomain

fun CurrentPriceData.toEntity() = CurrentPriceEntity(
    amount = bpi?.usd?.rate.orEmpty(),
    time = time?.updated.orEmpty()
)

fun CurrentPriceEntity.toDomain() = CurrentPriceDomain(
    amount = amount,
    time = time,
)