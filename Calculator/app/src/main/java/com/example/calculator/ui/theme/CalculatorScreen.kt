package com.example.calculator.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)


@Composable
        fun CalculatorScreen(modifier: Modifier = Modifier) {

               var numberString by remember { mutableStateOf("") }
               var secondNumber by remember { mutableStateOf("") }
               var numberFloat by remember { mutableFloatStateOf(0f) }
               var operation by remember { mutableStateOf(CalculatorOperation.Equals) }

                Column(modifier = modifier.padding(16.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
                        Text( text = numberString)

                        Row{
                                Button(onClick = { numberString += "7" }) {Text(text = "7")}
                                Button(onClick = { numberString += "8" }) {Text(text = "8")}
                                Button(onClick = { numberString += "9" }) {Text(text = "9")}
                                Button(onClick = { numberString = "" }) {Text(text = "C")}
                        }
                        Row{
                                Button(onClick = { numberString += "4" }) {Text(text = "4")}
                                Button(onClick = { numberString += "5" }) {Text(text = "5")}
                                Button(onClick = { numberString += "6" }) {Text(text = "6")}
                                Button(onClick = { secondNumber = numberString; operation = CalculatorOperation.Add; numberString = "" }) {Text(text = "+")}
                        }
                        Row {
                                Button(onClick = { numberString += "1" }) {Text(text = "1")}
                                Button(onClick = { numberString += "2" }) {Text(text = "2")}
                                Button(onClick = { numberString += "3" }) {Text(text = "3")}
                                Button(onClick = { secondNumber = numberString; operation = CalculatorOperation.Subtract; numberString = "" }) {Text(text = "-")}
                        }
                        Row {
                                Button(onClick = { /*TODO*/ }) {Text(text = "")}
                                Button(onClick = { numberString += "0" }) {Text(text = "0")}
                                Button(onClick = { /*TODO*/ }) {Text(text = ".")}
                                Button(onClick = {
                                        when(operation){
                                                CalculatorOperation.Add ->{ numberString = Soma(numberString.toFloat(), secondNumber.toFloat())}
                                                CalculatorOperation.Subtract ->{ numberString = Subtracao(numberString.toFloat(), secondNumber.toFloat())}
                                                else -> numberString.toFloat()
                                        }
                                }) {Text(text = "=")}
                        }
                }

        }

fun Soma( a: Float, b: Float ): String{
        var c = a + b

        return c.toString()

}

fun Subtracao( a: Float, b: Float ): String{
        var c = b - a

        return c.toString()

}

enum class CalculatorOperation{
        Add, Subtract, Multiply, Divide, Equals
}



