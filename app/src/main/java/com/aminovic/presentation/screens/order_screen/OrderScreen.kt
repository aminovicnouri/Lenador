package com.aminovic.presentation.screens.order_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.outlined.QrCode
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.aminovic.lenador.domain.modal.Product
import com.aminovic.presentation.composables.OrderItemsTable
import com.aminovic.presentation.composables.ProductItem
import com.aminovic.presentation.composables.add_product_dialog.AddProductDialog

@Composable
fun OrderScreen(
    state: OrderScreenState,
    onEvent: (OrderEvent) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
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
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(3f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.new_order),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 24.sp
                            )
                        )
                        Text(
                            text = stringResource(id = R.string.customer_id),
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
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
                    Divider(
                        thickness = 1.dp,
                        color = Color.DarkGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(0.7f)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        OrderItemsTable(state.order)
                    }
                    Divider(
                        thickness = 1.dp,
                        color = Color.DarkGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(0.7f)
                    )
                }

                Divider(
                    thickness = 1.dp,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .alpha(0.7f)
                )

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(2f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(onClick = {
                            onEvent(OrderEvent.ShowHideAddProductDialog(show = true))
                        }) {
                            Icon(
                                modifier = Modifier.size(48.dp),
                                imageVector = Icons.Filled.AddCircleOutline,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                modifier = Modifier.size(48.dp),
                                imageVector = Icons.Outlined.QrCode,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(128.dp),

                        // content padding
                        contentPadding = PaddingValues(
                            start = 12.dp,
                            top = 16.dp,
                            end = 12.dp,
                            bottom = 16.dp
                        ),
                        content = {
                            items(state.products) { item: Product ->
                                ProductItem(
                                    item = item,
                                    onClick = { onEvent(OrderEvent.AddProductToOrder(item)) })
                            }
                        }
                    )
                }
            }
        }
        if (state.addProductDialogShown) {
            AddProductDialog(
                onDismissed = { onEvent(OrderEvent.ShowHideAddProductDialog(show = false)) }
            )
        }
    }
}
