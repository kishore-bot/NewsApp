package com.example.newsapp.newsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.newsapp.domain.model.Article
import com.example.newsapp.newsapp.presentation.Dimens.ArticleCardSize
import com.example.newsapp.newsapp.presentation.Dimens.ExtraSmallPadding
import com.example.newsapp.newsapp.presentation.Dimens.SmallIconSize


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleCard(
    article: Article, onClick: (() -> Unit)? = null
) {

    val time = article.publishedAt.substring(11, 16)
    val context = LocalContext.current
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp, start = 10.dp, end = 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        onClick = { onClick?.invoke() }) {
        Row(
            modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(ArticleCardSize)
                    .clip(MaterialTheme.shapes.medium),
                model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                contentDescription = "Image of ${article.title}", // Add description
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = ExtraSmallPadding)
                    .height(ArticleCardSize),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = article.title,
                    style = MaterialTheme.typography.bodyMedium.copy(),
                    color = colorResource(id = R.color.text_title),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = article.source.name,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(id = R.color.body)
                    )
                    Spacer(modifier = Modifier.width(ExtraSmallPadding)) // Use consistent width
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_time),
                            contentDescription = "Published at", // Add description
                            modifier = Modifier.size(SmallIconSize),
                            tint = colorResource(id = R.color.body)
                        )
                        Spacer(modifier = Modifier.width(ExtraSmallPadding)) // Use consistent width
                        Text(
                            text = time,
                            style = MaterialTheme.typography.labelSmall,
                            color = colorResource(id = R.color.body)
                        )
                    }
                }
            }
        }
    }
}
