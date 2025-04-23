package com.pemrogamanmobile.mycalculatortipapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var billAmountInput: EditText
    private lateinit var tipPercentageDropdown: AutoCompleteTextView
    private lateinit var roundUpSwitch: Switch
    private lateinit var tipResult: TextView

    private val tipOptions = listOf("15%", "18%", "20%")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        billAmountInput = findViewById(R.id.billAmountInput)
        tipPercentageDropdown = findViewById(R.id.tipPercentageDropdown)
        roundUpSwitch = findViewById(R.id.roundUpSwitch)
        tipResult = findViewById(R.id.tipResult)

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tipOptions)
        tipPercentageDropdown.setAdapter(adapter)

        tipPercentageDropdown.setText(tipOptions[0], false)

        tipPercentageDropdown.setOnClickListener {
            tipPercentageDropdown.showDropDown()
        }

        tipPercentageDropdown.setOnItemClickListener { _, _, _, _ ->
            calculateTip()
        }

        billAmountInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calculateTip()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        roundUpSwitch.setOnCheckedChangeListener { _, _ ->
            calculateTip()
        }

        calculateTip()
    }

    private fun calculateTip() {
        val billAmount = billAmountInput.text.toString().toDoubleOrNull() ?: 0.0
        val tipPercentText = tipPercentageDropdown.text.toString()
        val tipPercent = tipPercentText.removeSuffix("%").toDoubleOrNull() ?: 0.0

        var tip = billAmount * (tipPercent / 100)
        if (roundUpSwitch.isChecked) {
            tip = ceil(tip)
        }

        tipResult.text = "Tip Amount: $%.2f".format(tip)
    }
}