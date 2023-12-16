package com.aminovic.presentation.screens.order_history_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aminovic.lenador.R
import com.aminovic.presentation.composables.TableRow
import java.util.Locale

@Composable
fun HistoryScreen(
    state: HistoryState,
    onEvent: (HistoryEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 25.dp)
    ) {
        Divider(
            thickness = 1.dp,
            color = Color.DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.7f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.orders),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 24.sp
                )
            )
        }
        Divider(
            thickness = 1.dp,
            color = Color.DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.7f)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column(Modifier.fillMaxSize()) {
                TableRow(
                    id = stringResource(R.string.id),
                    status = stringResource(id = R.string.status),
                    amount = stringResource(R.string.amount),
                    items = stringResource(R.string.items),
                    quantity = stringResource(id = R.string.quantity),
                    date = stringResource(R.string.date)
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    itemsIndexed(state.orders) { index, item ->
                        TableRow(
                            index = index,
                            textColor = Color.Black,
                            fontWeight = FontWeight.Normal,
                            id = item.id.toString(),
                            status = item.status.name,
                            amount = String.format(Locale.US, "%.2f", item.total),
                            items = item.items.size.toString(),
                            quantity = item.quantity.toString(),
                            date = item.time.toString()
                        )
                    }
                }
                TableRow(
                    id = stringResource(R.string.summary_as_per_the_filter),
                    status = "",
                    amount = String.format(Locale.US, "%.2f", state.total),
                    items = "${state.items}",
                    quantity = "${state.quantity}",
                    date = ""
                )
            }
        }
        Divider(
            thickness = 1.dp,
            color = Color.DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.7f)
        )
    }
}