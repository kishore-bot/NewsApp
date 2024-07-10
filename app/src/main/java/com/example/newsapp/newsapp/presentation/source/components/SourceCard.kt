package com.example.newsapp.newsapp.presentation.source.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.newsapp.domain.model.SourcesDetails
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun SourceCard(
    sourcesDetails: SourcesDetails, onClick: () -> Unit
) {
    Card(
        modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp).clickable { onClick() },
    ) {
        Column(
            horizontalAlignment = Alignment.Start, modifier = Modifier.padding(10.dp)
        ) {
            Row() {
                Text(text = "Name : ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = sourcesDetails.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Row() {
                Text(text = "Category : ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = sourcesDetails.category, fontSize = 20.sp, fontWeight = FontWeight.W700
                )
            }
            Row() {
                Text(text = "Description : ", fontSize = 20.sp, fontWeight = FontWeight.W700)
                Text(
                    text = sourcesDetails.description,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun P() {
    NewsAppTheme {
        SourceCard(
            onClick = {}, sourcesDetails = SourcesDetails(
                id = "associated-press",
                name = "Associated Press",
                description = "The AP delivers in-depth coverage on the international, politics, lifestyle, business, and entertainment news.",
                url = "https://apnews.com/",
                category = "general",
                language = "en",
                country = "us"
            )
        )
    }
}
