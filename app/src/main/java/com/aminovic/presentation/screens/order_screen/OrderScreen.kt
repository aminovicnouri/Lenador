package com.aminovic.presentation.screens.order_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.outlined.QrCode
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aminovic.lenador.R
import com.aminovic.lenador.domain.modal.Product
import com.aminovic.lenador.domain.utils.UiEvent
import com.aminovic.presentation.composables.OrderItemsTable
import com.aminovic.presentation.composables.ProductItem
import com.aminovic.presentation.composables.add_product_dialog.AddProductDialog
import kotlinx.coroutines.flow.Flow
import java.util.Locale

@Composable
fun OrderScreen(
    state: OrderScreenState,
    onEvent: (OrderEvent) -> Unit,
    popBackStack: () -> Unit,
    uiEvent: Flow<UiEvent>,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        uiEvent.collect { event ->
            when (event) {
                UiEvent.Success -> {
                    Toast.makeText(
                        context,
                        context.getText(R.string.order_saved_successfully), Toast.LENGTH_SHORT
                    ).show()
                    popBackStack()
                }

                else -> Unit
            }
        }
    }
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
                        OrderItemsTable(order = state.order, delete = {
                            onEvent(
                                OrderEvent.DeleteProductFromOrder(
                                    it
                                )
                            )
                        })
                    }
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
                            .height(160.dp)
                            .padding(horizontal = 10.dp)
                    ) {
                        Row(
                            Modifier
                                .fillMaxSize()
                                .padding(top = 5.dp)
                        ) {
                            Column {
                                PosButton(
                                    text = stringResource(id = R.string.cancel),
                                    icon = Icons.Default.Cancel,
                                    onClick = popBackStack
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                PosButton(
                                    text = stringResource(id = R.string.print),
                                    icon = Icons.Default.Print,
                                    onClick = {}
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .padding(horizontal = 20.dp)
                                    .padding(bottom = 10.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(Color.LightGray)
                                    .padding(10.dp)
                            ) {
                                Column(
                                    Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceAround
                                ) {
                                    Row(Modifier.fillMaxWidth()) {
                                        Text(
                                            modifier = Modifier
                                                .wrapContentHeight()
                                                .weight(1f), text = stringResource(
                                                R.string.total_items
                                            ), fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            modifier = Modifier
                                                .wrapContentHeight()
                                                .weight(1f),
                                            text = state.order.items.size.toString(),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                    Row(Modifier.fillMaxWidth()) {
                                        Text(
                                            modifier = Modifier
                                                .wrapContentHeight()
                                                .weight(1f), text = stringResource(
                                                R.string.quantity
                                            ), fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            modifier = Modifier
                                                .wrapContentHeight()
                                                .weight(1f),
                                            text = state.order.quantity.toString(),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                    Row(Modifier.fillMaxWidth()) {
                                        Text(
                                            modifier = Modifier
                                                .wrapContentHeight()
                                                .weight(1f),
                                            text = stringResource(R.string.subtotal),
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            modifier = Modifier
                                                .wrapContentHeight()
                                                .weight(1f),
                                            text = String.format(
                                                Locale.US,
                                                "%.2f",
                                                state.order.subTotal
                                            ),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                    Row(Modifier.fillMaxWidth()) {
                                        Text(
                                            modifier = Modifier
                                                .wrapContentHeight()
                                                .weight(1f),
                                            text = stringResource(R.string.tax),
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            modifier = Modifier
                                                .wrapContentHeight()
                                                .weight(1f),
                                            text = String.format(
                                                Locale.US,
                                                "%.2f",
                                                state.order.tax
                                            ),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                    Row(Modifier.fillMaxWidth()) {
                                        Text(
                                            modifier = Modifier
                                                .wrapContentHeight()
                                                .weight(1f),
                                            text = stringResource(R.string.discount),
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            modifier = Modifier
                                                .wrapContentHeight()
                                                .weight(1f),
                                            text = String.format(
                                                Locale.US,
                                                "%.2f",
                                                state.order.discount
                                            ),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                    Row(Modifier.fillMaxWidth()) {
                                        Text(
                                            modifier = Modifier
                                                .wrapContentHeight()
                                                .weight(1f),
                                            text = stringResource(R.string.total),
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            modifier = Modifier
                                                .wrapContentHeight()
                                                .weight(1f),
                                            text = String.format(
                                                Locale.US,
                                                "%.2f",
                                                state.order.total
                                            ),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }

                            Column {
                                PosButton(
                                    text = stringResource(R.string.payment),
                                    icon = Icons.Default.Payment,
                                    onClick = {}
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                PosButton(
                                    text = "Suspend",
                                    icon = Icons.Default.Pause,
                                    onClick = {
                                        onEvent(OrderEvent.SaveOrder)
                                    }
                                )
                            }
                        }
                    }
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


@Composable
fun PosButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable { onClick() }
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = icon,
                contentDescription = null,
                tint = Color.White
            )
            Text(
                text = text,
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}
