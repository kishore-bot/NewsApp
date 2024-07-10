package com.example.newsapp.newsapp.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.newsapp.domain.model.Article
import com.example.newsapp.newsapp.presentation.Dimens.ArticleImageHeight
import com.example.newsapp.newsapp.presentation.Dimens.MediumPadding1
import com.example.newsapp.newsapp.presentation.details.components.DetailsTopAppBar


@Composable
fun DetailsScreen(
    article: Article, event: (DetailsEvent) -> Unit, navigateUp: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopAppBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }

            },
            onBookMarkCLick = { event(DetailsEvent.UpsertDeleteArticle(article)) },
            onBackClick = navigateUp,
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(
                start = MediumPadding1, end = MediumPadding1, top = MediumPadding1
            )
        ) {
            item {

                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(text = "Author:  ", style = MaterialTheme.typography.labelLarge)
                    if (article.author?.isNotEmpty() == true) {
                        Text(
                            text = article.author,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        Text(text = "Author Not Found", style = MaterialTheme.typography.labelLarge)
                    }

                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(text = "Source:  ", style = MaterialTheme.typography.labelLarge)

                    Text(
                        text = article.source.name.uppercase(),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )


                }
                Spacer(modifier = Modifier.height(20.dp))
                val year = article.publishedAt.substring(0, 9)// Extract year
                val time = article.publishedAt.substring(11, 16)
                Row {
                    Text(text = "Published Year:  ", style = MaterialTheme.typography.labelLarge)

                    Text(
                        text = year,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )


                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(text = "Published Time:  ", style = MaterialTheme.typography.labelLarge)

                    Text(
                        text = time,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )


                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(
                        id = R.color.body
                    )
                )
            }
        }
    }
}
