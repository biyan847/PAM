package com.example.pam.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pam.R
import com.example.pam.ui.theme.PAMTheme
import com.example.pam.ui.theme.Purple80

@Composable
fun HalamaHome(oneNextButtonClicked: () -> Unit) {
    val image = painterResource(id = R.drawable.sendok)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.Black else Purple80),

        ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(5.dp)

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(400.dp)
                        .padding(12.dp),


                    )
                Text(
                    text = "nama pelanggan",
                    color = Color.DarkGray,
                    fontSize = 35.sp,
                    modifier = Modifier.padding(12.dp)
                )
                Text(
                    text = "Nomer meja",
                    color = Color.DarkGray,
                    fontSize = 35.sp,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = oneNextButtonClicked
            ) {
                Text(text = "Pilih Menu")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewHalamaHome(){
    PAMTheme {
        HalamaHome (oneNextButtonClicked = {})
    }
}