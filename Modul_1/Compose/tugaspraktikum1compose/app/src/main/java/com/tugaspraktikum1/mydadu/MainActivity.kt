package com.tugaspraktikum1.mydadu

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tugaspraktikum1.mydadu.ui.theme.MyDaduTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDaduTheme {
                    DiceGame { message ->
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}

@Composable
fun DiceGame(onResult: (String) -> Unit) {

    var dadu1 by remember { mutableIntStateOf(0) }
    var dadu2 by remember { mutableIntStateOf(0) }

    fun rollDice() {
        dadu1 = buatangkadadu()
        dadu2 = buatangkadadu()
        val resultMessage = if (dadu1 == dadu2) {
            "Selamat, anda dapat dadu double!"
        } else {
            "Anda belum beruntung!"
        }
        onResult(resultMessage)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            DiceView(sisidadu = dadu1)
            DiceView(sisidadu = dadu2)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { rollDice() },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Roll")
        }
    }
}

@Composable
fun DiceView(sisidadu : Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = ambilgambardadu(sisidadu)),
            contentDescription = "tampilan dadu adalah yang ke $sisidadu",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Fit
        )
    }
}

fun buatangkadadu(): Int {
    val angkadadu = Random.nextInt(1, 7)
    return angkadadu
}

fun ambilgambardadu(number: Int): Int {
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