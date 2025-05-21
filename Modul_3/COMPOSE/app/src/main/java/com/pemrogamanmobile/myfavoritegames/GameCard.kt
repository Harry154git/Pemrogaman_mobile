package com.pemrogamanmobile.myfavoritegames

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.pemrogamanmobile.myfavoritegames.model.Games
import androidx.compose.ui.layout.ContentScale

@Composable
fun GameCard(
    games: Games,
    modifier: Modifier = Modifier,
    onDetailClick: (Games) -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = modifier.padding(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF343539))
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {

            Image(
                painter = painterResource(games.imageResourceId),
                contentDescription = stringResource(games.titleResourceId),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .width(100.dp)
                    .height(140.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = context.getString(games.titleResourceId),
                        style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.White),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = context.getString(games.yearResourceId),
                        style = TextStyle(fontSize = 16.sp, color = Color(0xFF7C7C86)),
                        modifier = Modifier
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "tentang game ini:",
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    )
                    Text(
                        text = context.getString(games.detailResourceId),
                        style = TextStyle(fontSize = 14.sp, color = Color.White),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            val url = context.getString(games.linkResourceId)
                            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                            context.startActivity(intent)
                        },
                        modifier = Modifier.width(100.dp).height(44.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFb0c4ff), contentColor = Color.White)
                    ) {
                        Text("View", style = TextStyle(fontSize = 16.sp, color = Color.Black))
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = { onDetailClick(games) },
                        modifier = Modifier.width(100.dp).height(44.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFb0c4ff), contentColor = Color.White)
                    ) {
                        Text("Detail", style = TextStyle(fontSize = 16.sp, color = Color.Black))
                    }
                }
            }
        }
    }
}