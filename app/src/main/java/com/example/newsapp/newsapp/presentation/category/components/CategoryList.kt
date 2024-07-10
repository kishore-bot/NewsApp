package com.example.newsapp.newsapp.presentation.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CategoryList(
    category: String, isSelected: Boolean, onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
        .padding(start = 10.dp)
        .width(180.dp)
            .clip(RoundedCornerShape(10.dp))
        .clickable { onClick() }
        .background(
            color = when (isSelected) {
                true -> MaterialTheme.colorScheme.primaryContainer
                else -> MaterialTheme.colorScheme.tertiaryContainer
            }
        )) {
        Text(
            modifier = Modifier
                .padding(10.dp),
            text = category.uppercase(),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
    }

}