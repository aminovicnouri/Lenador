package com.aminovic.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.aminovic.lenador.R
import com.aminovic.lenador.domain.modal.Order
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.TableColumnWidth
import com.seanproctor.datatable.material3.PaginatedDataTable
import com.seanproctor.datatable.paging.rememberPaginatedDataTableState
import java.util.Locale


@Composable
fun OrderItemsTable(order: Order) {
    PaginatedDataTable(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        state = rememberPaginatedDataTableState(6),
        columns = listOf(
            DataColumn(width = TableColumnWidth.Flex(3f)) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.name),
                    color = Color.White
                )
            },
            DataColumn(width = TableColumnWidth.Flex(1f)) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.price),
                    color = Color.White
                )
            },
            DataColumn(width = TableColumnWidth.Flex(1f)) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.tax),
                    color = Color.White
                )
            },
            DataColumn(width = TableColumnWidth.Flex(1f)) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.discount),
                    color = Color.White
                )
            },
            DataColumn(width = TableColumnWidth.Flex(1f)) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.quantity),
                    color = Color.White
                )
            },
            DataColumn(width = TableColumnWidth.Flex(1f)) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.total),
                    color = Color.White
                )
            },
            DataColumn(width = TableColumnWidth.Flex(1f)) {},
        ),
    ) {
        for (item in order.items) {
            row {
                cell { Text(item.product.name, color = Color.White) }
                cell { Text(item.product.price.toString(), color = Color.White) }
                cell { Text(String.format(Locale.US, "%.2f", item.tax), color = Color.White) }
                cell { Text(String.format(Locale.US, "%.2f", item.discount), color = Color.White) }
                cell { Text(item.quantity.toString(), color = Color.White) }
                cell { Text(String.format(Locale.US, "%.2f", item.total), color = Color.White) }
            }
        }
    }
}