package com.example.newsapp.newsapp.presentation.source

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.newsapp.domain.model.SourcesDetails
import com.example.newsapp.newsapp.presentation.Dimens
import com.example.newsapp.newsapp.presentation.source.components.SourceList

@Composable
fun SourceScreen(
    sources: LazyPagingItems<SourcesDetails>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimens.MediumPadding1)
            .statusBarsPadding()
    ) {
        Text(
            text = "News Sources",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(
                id = R.color.text_title
            )
        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        SourceList(sources = sources)
    }
}