package com.glechyk.obrio_testapp.presentation.feature.transaction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TransactionRoute(
    viewModel: TransactionViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {
    val balanceState = viewModel.balanceState.collectAsState()

    TransactionScreen(
        balance = balanceState.value,
        onAddButtonClick = { amount, category ->
            viewModel.decreaseTransaction(amount, category)
            onNavigateBack()
        }
    )
}