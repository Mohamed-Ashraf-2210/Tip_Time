package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout
import java.text.NumberFormat
import kotlin.math.ceil



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val costField = binding.costOfService.editText?.text.toString()
        val cost = costField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }
        val tipPercentage = getTipPercentage()
        var tips = cost * tipPercentage
        val roundUp = binding.switchId.isChecked
        if (roundUp) {
            tips = ceil(tips)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tips)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun getTipPercentage() = when(binding.tipOptions.checkedRadioButtonId) {
            R.id.amazing_option -> 0.20
            R.id.good_option -> 0.18
            else -> 0.15
        }
}