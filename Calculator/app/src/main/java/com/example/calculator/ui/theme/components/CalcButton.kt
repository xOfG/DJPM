package com.example.calculator.ui.theme.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.Pink80
import com.example.calculator.ui.theme.Purple40

@Preview(showBackground = true)


@Composable
fun fancyButton(
    modifier: Modifier = Modifier,
    label : String = "",
    isOperation : Boolean = false,
    onClick : (String) -> Unit = {}
){
    Button(
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp),
        colors = if (isOperation)
            ButtonDefaults.run { buttonColors(Purple40) }
        else
            ButtonDefaults.run { buttonColors(Pink80) },
        onClick = {
            onClick(label)
        }) {
        Text(text = label,
            fontSize = 42.sp,
            style = MaterialTheme.typography.bodyLarge
            )
    }
}