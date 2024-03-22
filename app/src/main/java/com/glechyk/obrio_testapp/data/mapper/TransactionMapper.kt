package com.glechyk.obrio_testapp.data.mapper

import com.glechyk.obrio_testapp.data.db.entity.TransactionEntity
import com.glechyk.obrio_testapp.domain.model.TransactionDomain

fun TransactionEntity.toDomain() = category?.let {
    TransactionDomain.Decrease(
        amount = amount,
        category = it,
        time = time,
    )
} ?: TransactionDomain.Increase(
    amount = amount,
    time = time,
)

fun TransactionDomain.toEntity() = when (this) {
    is TransactionDomain.Increase -> TransactionEntity(
        amount = amount,
        category = null,
        time = time,
    )

    is TransactionDomain.Decrease -> TransactionEntity(
        amount = amount,
        category = category,
        time = time,
    )
}