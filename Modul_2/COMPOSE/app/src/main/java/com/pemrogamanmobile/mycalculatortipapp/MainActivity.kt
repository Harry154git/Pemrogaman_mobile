package com.pemrogamanmobile.mycalculatortipapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mycalculatortip(
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mycalculatortip() {
    val options = listOf("15%", "18%", "20%")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    var billAmount by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) }

    val tipPercentage = selectedOptionText.removeSuffix("%").toDoubleOrNull() ?: 0.0
    val bill = billAmount.toDoubleOrNull() ?: 0.0
    var tip = bill * (tipPercentage / 100.0)
    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }

    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Calculate Tip",
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )

            TextField(
                value = billAmount,
                onValueChange = { billAmount = it },
                label = { Text("Bill Amount") },
                leadingIcon = {
                    Icon(Icons.Filled.Money, contentDescription = "Money Icon")
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                TextField(
                    readOnly = true,
                    value = selectedOptionText,
                    onValueChange = {},
                    label = { Text("Tip Percentage") },
                    leadingIcon = {
                        Icon(Icons.Filled.Percent, contentDescription = "Percent Icon")
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                            }
                        )
                    }
                }
            }


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(text = "Round up tip?", fontSize = 20.sp)
                androidx.compose.material3.Switch(
                    checked = roundUp,
                    onCheckedChange = { roundUp = it }
                )
            }

            Text(
                text = "Tip Amount: $${"%.2f".format(tip)}",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}