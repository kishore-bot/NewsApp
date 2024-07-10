package com.example.newsapp.newsapp.presentation.onbording.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CountyCard(
    country: Country,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .clickable {
                onClick()
            }
            .background(
                color = when (isSelected) {
                    true -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.inversePrimary
                }
            )
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                modifier = Modifier
                    .width(80.dp)
                    .height(50.dp),

                painter = painterResource(country.flag),

                contentDescription = country.name,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(40.dp))
            Text(
                text = country.name.toString(),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary, // Use onPrimary for selected state
                fontSize = 20.sp
            )
        }
    }
}
