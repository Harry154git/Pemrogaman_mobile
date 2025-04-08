package com.pemrogamanmobile.mydadu

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var dice1Image: ImageView
    private lateinit var dice2Image: ImageView
    private lateinit var rollButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dice1Image = findViewById(R.id.dice1Image)
        dice2Image = findViewById(R.id.dice2Image)
        rollButton = findViewById(R.id.rollButton)

        rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val dadu1 = buatangkadadu()
        val dadu2 = buatangkadadu()

        dice1Image.setImageResource(ambilgambardadu(dadu1))
        dice2Image.setImageResource(ambilgambardadu(dadu2))

        val resultMessage = if (dadu1 == dadu2) {
            "Selamat, anda dapat dadu double!"
        } else {
            "Anda belum beruntung!"
        }

        Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT).show()
    }

    private fun buatangkadadu(): Int {
        return Random.nextInt(1, 7)
    }

    private fun ambilgambardadu(number: Int): Int {
        return when (number) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_0
        }
    }
}
