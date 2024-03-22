package com.glechyk.obrio_testapp.presentation.feature.balance

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun BalanceRoute(
    viewModel: BalanceViewModel = hiltViewModel(),
    onNavigateToTransaction: () -> Unit,
) {
    val currentPriceState = viewModel.currentPriceState.collectAsState()
    val balanceState = viewModel.balanceState.collectAsState()
    val paging = viewModel.transactionsList.collectAsLazyPagingItems()

    BalanceScreen(
        currentPrice = currentPriceState.value,
        balance = balanceState.value.toString(),
        onUpdateBalanceClick = { viewModel.updateBalance(it) },
        transactions = paging,
        onNavigateToTransaction = onNavigateToTransaction,
    )
}