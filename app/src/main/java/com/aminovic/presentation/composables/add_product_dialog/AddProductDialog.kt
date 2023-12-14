package com.aminovic.presentation.composables.add_product_dialog

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aminovic.lenador.R
import com.aminovic.lenador.domain.utils.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductDialog(
    onDismissed: () -> Unit,
    addProductViewModel: AddProductViewModel = hiltViewModel(),
) {
    val state by addProductViewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    BackHandler {
        addProductViewModel.onEvent(AddProductEvent.ClearInputs)
        onDismissed()
    }

    // Collect and handle UI events related to pop-up dialogs.
    LaunchedEffect(key1 = true) {
        addProductViewModel.uiEvent.collect { event ->
            when (event) {
                UiEvent.Success -> {
                    Toast.makeText(
                        context,
                        context.getText(R.string.product_added_successfully), Toast.LENGTH_SHORT
                    ).show()
                    onDismissed()
                }

                else -> Unit
            }
        }
    }

    Dialog(onDismissRequest = {
        addProductViewModel.onEvent(AddProductEvent.ClearInputs)
        onDismissed()
    }, properties = DialogProperties(usePlatformDefaultWidth = true)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White)
                    .padding(horizontal = 10.dp, vertical = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.add_new_item),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 24.sp
                    )
                )
                Spacer(modifier = Modifier.height(25.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(100.dp),
                        text = stringResource(R.string.name),
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 22.sp
                        )
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .height(50.dp)
                            .weight(1f),
                        value = state.product.name,
                        onValueChange = { addProductViewModel.onEvent(AddProductEvent.UpdateName(it)) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(100.dp),
                        text = stringResource(R.string.price),
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 22.sp
                        )
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .height(50.dp)
                            .weight(1f),
                        value = state.price,
                        onValueChange = {
                            addProductViewModel.onEvent(AddProductEvent.UpdatePrice(it))
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(100.dp),
                        text = stringResource(R.string.barcode),
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 22.sp
                        )
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .height(50.dp)
                            .weight(1f),
                        value = state.product.barcode,
                        onValueChange = {
                            addProductViewModel.onEvent(AddProductEvent.UpdateBarcode(it))
                        },
                    )
                }
                state.errorMessage?.let {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = state.errorMessage!!.asString(context),
                        style = TextStyle(
                            color = Color.Red,
                            fontSize = 14.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = { addProductViewModel.onEvent(AddProductEvent.Save) }) {
                    Text(
                        text = stringResource(R.string.save),
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

