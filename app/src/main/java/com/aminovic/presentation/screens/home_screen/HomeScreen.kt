package com.aminovic.presentation.screens.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aminovic.lenador.R
import com.aminovic.presentation.composables.HomeItem

@Composable
fun HomeScreen(
    navigateToNewOrder: () -> Unit,
    navigateToOrders: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp, horizontal = 25.dp)
    ) {
        Text(
            text = "Abu Dhabi Arabic Language Centre",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 24.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            HomeItem(
                title = stringResource(R.string.new_order),
                icon = Icons.Filled.NoteAdd,
                onClick = navigateToNewOrder)
            Spacer(modifier = Modifier.width(20.dp))
            HomeItem(
                title = stringResource(R.string.orders),
                icon = Icons.Filled.FileCopy,
                onClick = {})
            Spacer(modifier = Modifier.width(20.dp))
            HomeItem(
                title = stringResource(R.string.report),
                icon = Icons.Filled.Insights,
                onClick = {})
        }
    }
}
