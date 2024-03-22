package com.glechyk.obrio_testapp.presentation.feature.balance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.glechyk.obrio_testapp.domain.model.BalanceDomain
import com.glechyk.obrio_testapp.domain.model.TransactionDomain
import com.glechyk.obrio_testapp.domain.usecase.GetBalanceUseCase
import com.glechyk.obrio_testapp.domain.usecase.GetCurrentPriceUseCase
import com.glechyk.obrio_testapp.domain.usecase.GetTransactionsListUseCase
import com.glechyk.obrio_testapp.domain.usecase.InsertTransactionUseCase
import com.glechyk.obrio_testapp.domain.usecase.UpdateBalanceUseCase
import com.glechyk.obrio_testapp.utils.subscribe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BalanceViewModel @Inject constructor(
    private val getCurrentPriceUseCase: GetCurrentPriceUseCase,
    private val getBalanceUseCase: GetBalanceUseCase,
    private val updateBalanceUseCase: UpdateBalanceUseCase,
    private val insertTransactionUseCase: InsertTransactionUseCase,
    private val getTransactionsListUseCase: GetTransactionsListUseCase,
) : ViewModel() {

    private val _currentPriceState = MutableStateFlow(Pair("", ""))
    val currentPriceState = _currentPriceState.asStateFlow()

    private val _balanceState = MutableStateFlow(0.0)
    val balanceState = _balanceState.asStateFlow()

    private val _transactionsList =
        MutableStateFlow<PagingData<TransactionDomain>>(PagingData.empty())
    val transactionsList = _transactionsList.asStateFlow()

    init {
        viewModelScope.launch {
            getCurrentPriceUseCase().subscribe(
                scope = this,
                success = { currentPrice ->
                    _currentPriceState.update { Pair(currentPrice.amount, currentPrice.time) }
                },
                error = {
                    it.printStackTrace()
                },
            )
            getBalanceUseCase().subscribe(
                scope = this,
                success = { balance ->
                    _balanceState.update { balance.amount }
                },
                error = {
                    it.printStackTrace()
                },
            )
            getTransactionsListUseCase().subscribe(
                scope = this,
                success = { transactions ->
                    _transactionsList.update { transactions }
                },
                error = {
                    it.printStackTrace()
                },
            )
        }
    }

    fun updateBalance(amount: String) {
        viewModelScope.launch {
            val updatedBalance = _balanceState.value + amount.toDouble()
            updateBalanceUseCase(
                BalanceDomain(
                    amount = updatedBalance
                )
            )
            insertTransactionUseCase(
                TransactionDomain.Increase(
                    amount = amount.toDouble(),
                    time = System.currentTimeMillis()
                )
            )
            _balanceState.update { updatedBalance }
        }
    }
}
