package com.aminovic.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TableRow(
    index: Int = -1,
    textColor: Color = Color.White,
    fontWeight: FontWeight = FontWeight.SemiBold,
    id: String,
    status: String,
    amount: String,
    items: String,
    quantity: String,
    date: String,
) {
    Column(Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 5.dp)
                .background(
                    if (index == -1) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        if (index.mod(2) == 0) Color.White else MaterialTheme.colorScheme.tertiary
                    }
                )
                .padding(vertical = 5.dp, horizontal = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = id,
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(1f),
                    color = textColor,
                    fontWeight = fontWeight,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = status,
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(2f),
                    color = textColor,
                    fontWeight = fontWeight,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = amount,
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(2f),
                    color = textColor,
                    fontWeight = fontWeight,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = items,
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(1f),
                    color = textColor,
                    fontWeight = fontWeight,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = quantity,
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(1f),
                    color = textColor,
                    fontWeight = fontWeight,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = date,
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(2f),
                    color = textColor,
                    fontWeight = fontWeight,
                    textAlign = TextAlign.Center
                )
            }
        }
        Divider(
            thickness = 1.dp,
            color = Color.DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
                .alpha(0.7f)
        )
    }
}