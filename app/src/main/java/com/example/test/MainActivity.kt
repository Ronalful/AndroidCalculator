package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculator()
        }
    }
}

@Composable
fun Calculator() {

    val number = remember { mutableStateOf("0") }
    val secondNumber = remember { mutableStateOf("0") }
    val operation = remember { mutableStateOf(Operations.None) }

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 40.dp),
        verticalArrangement = Arrangement.Center) {
        Text(number.value, fontSize = 40.sp, modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black))
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items((1..9).toList()) {
                Button(
                    onClick = {
                        if (number.value == "0") number.value = it.toString() else number.value += it.toString()
                    },
                    shape = RoundedCornerShape(0)) {
                    Text(it.toString(), fontSize = 30.sp)
                }
            }
        }

        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = {
                    if (number.value == "0") number.value = "0" else number.value += "0"
                },
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0)) {
                Text("0", fontSize = 30.sp)
            }
        }

        LazyVerticalGrid(columns = GridCells.Fixed(4)) {
            item {
                Button(
                    onClick = {
                        operation.value = Operations.Add
                        secondNumber.value = number.value
                        number.value = "0"
                    },
                    shape = RoundedCornerShape(0)
                ) {
                    Text("+", fontSize = 30.sp)
                }
            }
            item {
                Button(
                    onClick = {
                        operation.value = Operations.Sub
                        secondNumber.value = number.value
                        number.value = "0"
                    },
                    shape = RoundedCornerShape(0)
                ) {
                    Text("-", fontSize = 30.sp)
                }
            }
            item {
                Button(
                    onClick = {
                        operation.value = Operations.Mul
                        secondNumber.value = number.value
                        number.value = "0"
                    },
                    shape = RoundedCornerShape(0)
                ) {
                    Text("*", fontSize = 30.sp)
                }
            }
            item {
                Button(
                    onClick = {
                        operation.value = Operations.Div
                        secondNumber.value = number.value
                        number.value = "0"
                    },
                    shape = RoundedCornerShape(0)) {
                    Text("/", fontSize = 30.sp)
                }
            }

        }

        Row {
            Button(
                onClick = {
                    when(operation.value) {
                        Operations.Add -> {
                            number.value = (secondNumber.value.toInt() + number.value.toInt()).toString()
                            operation.value = Operations.None
                        }
                        Operations.Sub -> {
                            number.value = (secondNumber.value.toInt() - number.value.toInt()).toString()
                            operation.value = Operations.None
                        }
                        Operations.Mul -> {
                            number.value = (secondNumber.value.toInt() * number.value.toInt()).toString()
                            operation.value = Operations.None
                        }
                        Operations.Div -> {
                            if (number.value != "0") {
                                number.value = (secondNumber.value.toInt() / number.value.toInt()).toString()
                                operation.value = Operations.None
                            }
                        }
                        Operations.None -> {}
                    }
                 },
                shape = RoundedCornerShape(0)
            ) {
                Text("Enter", fontSize = 30.sp)
            }
            Button(onClick = { number.value = "0"; operation.value = Operations.None }, shape = RoundedCornerShape(0)) {
                Text("Clear", fontSize = 30.sp)
            }
        }


    }
}

enum class Operations {
    Add, Sub, Mul, Div, None
}





