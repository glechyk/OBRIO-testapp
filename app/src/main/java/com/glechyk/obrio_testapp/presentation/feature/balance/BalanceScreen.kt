package com.glechyk.obrio_testapp.presentation.feature.balance

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.glechyk.obrio_testapp.R
import com.glechyk.obrio_testapp.domain.model.TransactionDomain
import com.glechyk.obrio_testapp.utils.toParsedDate
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalanceScreen(
    currentPrice: Pair<String, String>,
    balance: String,
    transactions: LazyPagingItems<TransactionDomain>,
    onUpdateBalanceClick: (String) -> Unit,
    onNavigateToTransaction: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Bitcoins")
                },
                actions = {
                    Text(
                        text = "$ ${currentPrice.first}",
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                scope.launch {
                                    snackBarHostState.showSnackbar("Updated ${currentPrice.second}")
                                }
                            },
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AccountInfoBlock(
                amount = balance, onAddButtonClick = { showDialog = true }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onNavigateToTransaction, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Add Transaction")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(14.dp)
                    )
                    .padding(vertical = 10.dp, horizontal = 16.dp),
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (transactions.loadState.refresh is LoadState.Loading) {
                        item {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    } else {
                        items(transactions.itemSnapshotList) { transaction ->
                            if (transaction != null) {
                                when (transaction) {
                                    is TransactionDomain.Increase -> IncreaseTransactionItem(
                                        amount = transaction.amount.toString(),
                                        time = transaction.time.toParsedDate(),
                                    )

                                    is TransactionDomain.Decrease -> DecreaseTransactionItem(
                                        time = transaction.time.toParsedDate(),
                                        amount = transaction.amount.toString(),
                                        category = transaction.category.value,
                                    )
                                }
                            }
                        }
                        item {
                            if (transactions.loadState.append is LoadState.Loading) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    IncreaseBalanceDialog(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        onConfirm = { onUpdateBalanceClick(it) }
    )
}

@Composable
private fun AccountInfoBlock(
    amount: String,
    onAddButtonClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.btc_logo),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Balance", style = MaterialTheme.typography.titleMedium,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "BTC $amount", style = MaterialTheme.typography.bodyMedium,
            )
        }
        IconButton(onClick = onAddButtonClick) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}

@Composable
private fun IncreaseTransactionItem(amount: String, time: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = " + $amount", style = MaterialTheme.typography.titleSmall, color = Color.Green)

        Text(text = time, style = MaterialTheme.typography.bodySmall)
    }

    Spacer(modifier = Modifier.height(16.dp))

    HorizontalDivider(color = Color.White)
}

@Composable
private fun DecreaseTransactionItem(time: String, amount: String, category: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = " - $amount", style = MaterialTheme.typography.titleSmall, color = Color.Red)

        Text(text = time, style = MaterialTheme.typography.bodySmall)

        Text(
            text = category.uppercase(),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.W700
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    HorizontalDivider(color = Color.White)
}

@Composable
private fun IncreaseBalanceDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    if (showDialog) {
        var textValue by remember { mutableStateOf(TextFieldValue()) }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Increase BTC") },
            text = {
                Column {
                    TextField(
                        value = textValue,
                        onValueChange = { newText ->
                            if (newText.text.isEmpty() || newText.text.toDoubleOrNull() != null) {
                                textValue = newText
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Decimal
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm(textValue.text)
                        onDismiss()
                    },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
