package com.glechyk.obrio_testapp.presentation.feature.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glechyk.obrio_testapp.domain.model.Category
import com.glechyk.obrio_testapp.domain.usecase.AddTransactionUseCase
import com.glechyk.obrio_testapp.domain.usecase.GetBalanceUseCase
import com.glechyk.obrio_testapp.utils.subscribe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val getBalanceUseCase: GetBalanceUseCase,
    private val addTransactionUseCase: AddTransactionUseCase,
) : ViewModel() {

    private val _balanceState = MutableStateFlow(0.0)
    val balanceState = _balanceState.asStateFlow()

    init {
        viewModelScope.launch {
            getBalanceUseCase().subscribe(
                scope = this,
                success = { balance ->
                    _balanceState.update { balance.amount }
                },
                error = {
                    it.printStackTrace()
                },
            )
        }
    }

    fun decreaseTransaction(amount: String, category: Category) {
        viewModelScope.launch {
            addTransactionUseCase(
                balanceAmount = _balanceState.value,
                transactionAmount = amount.toDouble(),
                category = category,
            )
        }
    }
}