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
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.components.fancyButton

@Preview(showBackground = true)

@Composable
        fun CalculatorScreen(modifier: Modifier = Modifier) {

               var numberString by remember { mutableStateOf("0") }
               var secondNumber by remember { mutableStateOf("") }
               /*var numberFloat by remember { mutableFloatStateOf(0f) }*/
               var operation by remember { mutableStateOf(CalculatorOperation.Equals) }

                Column(modifier = modifier.padding(16.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
                        Text( text = numberString, fontSize = 52.sp)

                        Row{
                                fancyButton(label = "7",onClick = { numberString = AddNumber(numberString, "7") },modifier = Modifier.weight(1f) )
                                fancyButton(label = "8",onClick = { numberString = AddNumber(numberString, "8") },modifier = Modifier.weight(1f) )
                                fancyButton(label = "9",onClick = { numberString = AddNumber(numberString, "9") },modifier = Modifier.weight(1f) )
                                fancyButton(label = "C", isOperation = true, onClick = { numberString = "0" },modifier = Modifier.weight(1f) )
                        }
                        Row{
                                fancyButton(label = "4",onClick = { numberString = AddNumber(numberString, "4") },modifier = Modifier.weight(1f) )
                                fancyButton(label = "5",onClick = { numberString = AddNumber(numberString, "5") },modifier = Modifier.weight(1f) )
                                fancyButton(label = "6",onClick = { numberString = AddNumber(numberString, "6") },modifier = Modifier.weight(1f) )
                                fancyButton(label = "+", isOperation = true, onClick = { secondNumber = numberString; operation = CalculatorOperation.Add; numberString = "0" },modifier = Modifier.weight(1f) )
                        }
                        Row {
                                fancyButton(label = "1",onClick = { numberString = AddNumber(numberString, "1") },modifier = Modifier.weight(1f))
                                fancyButton(label = "2",onClick = { numberString = AddNumber(numberString, "2") },modifier = Modifier.weight(1f))
                                fancyButton(label = "3",onClick = { numberString = AddNumber(numberString, "3") },modifier = Modifier.weight(1f))
                                fancyButton(label = "-", isOperation = true, onClick = { secondNumber = numberString; operation = CalculatorOperation.Subtract; numberString = "0" },modifier = Modifier.weight(1f))
                        }
                        Row {
                                fancyButton(label = "" ,onClick = { /*TODO*/ },modifier = Modifier.weight(1f))
                                fancyButton(label = "0",onClick = { numberString = AddNumber(numberString, "0") },modifier = Modifier.weight(1f) )
                                fancyButton(label = ".",onClick = { numberString = AddNumber(numberString, ".") },modifier = Modifier.weight(1f) )
                                fancyButton(label = "=", isOperation = true, onClick = {
                                        when(operation){
                                                CalculatorOperation.Add ->{ numberString = Soma(numberString.toFloat(), secondNumber.toFloat())}
                                                CalculatorOperation.Subtract ->{ numberString = Subtracao(numberString.toFloat(), secondNumber.toFloat())}
                                                else -> numberString.toFloat()
                                        }
                                },modifier = Modifier.weight(1f))
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
        Add, Subtract, Multiply, Divide, Equals, Percentage
}

fun AddNumber(number: String, secondNumber: String): String{
        if( number == "0" ) return "" + secondNumber
        else return number + secondNumber
}



