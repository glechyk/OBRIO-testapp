package com.glechyk.obrio_testapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.glechyk.obrio_testapp.domain.model.Category

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val amount: Double,
    val category: Category? = null,
    val time: Long,
)
