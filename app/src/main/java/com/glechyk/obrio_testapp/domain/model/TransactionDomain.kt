package com.glechyk.obrio_testapp.domain.model

sealed class TransactionDomain {

    data class Increase(
        val amount: Double,
        val time: Long,
    ) : TransactionDomain()

    data class Decrease(
        val amount: Double,
        val category: Category,
        val time: Long,
    ) : TransactionDomain()
}

enum class Category(val value: String) {
    GROCERIES("groceries"),
    TAXI("taxi"),
    ELECTRONICS("electronics"),
    RESTAURANT("restaurant"),
    OTHER("other")
}
