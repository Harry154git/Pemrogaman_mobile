package com.pemrogamanmobile.myfavoritegames

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(titleResId: Int, imageResId: Int, yearResId: Int, detailResId: Int) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF131418)
    ) {

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {

            Image(
                painter = painterResource(imageResId),
                contentDescription = stringResource(detailResId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(titleResId),
                        style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.White),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = stringResource(yearResId),
                        style = TextStyle(fontSize = 16.sp, color = Color(0xFF7C7C86)),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "tentang game ini:",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                )

                Text(
                    text = stringResource(detailResId),
                    style = TextStyle(fontSize = 14.sp, color = Color.White),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}