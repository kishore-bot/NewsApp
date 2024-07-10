package com.example.newsapp.newsapp.presentation.source.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.newsapp.domain.model.SourcesDetails
import com.example.newsapp.newsapp.presentation.Dimens
import com.example.newsapp.newsapp.presentation.common.EmptyScreen
import com.example.newsapp.newsapp.presentation.common.SourceCardShimmerEffect


@Composable
fun SourceList(
    modifier: Modifier = Modifier,
    sources: LazyPagingItems<SourcesDetails>,
) {
    val context = LocalContext.current
    val handlePagingResult = handlePagingResult(sources = sources)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
            contentPadding = PaddingValues(all = Dimens.ExtraSmallPadding2)
        ) {
            items(
                count = sources.itemCount
            ) { it ->
                sources[it]?.let {
                    SourceCard(sourcesDetails = it, onClick = {
                        Intent(Intent.ACTION_VIEW).also {intent->
                            intent.data = Uri.parse(it.url)
                            if (intent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(intent)
                            }
                        }
                    })
                }
            }
        }
    }

}


@Composable
fun handlePagingResult(
    sources: LazyPagingItems<SourcesDetails>
): Boolean {

    val loadState = sources.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null

    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen()
            false
        }

        else -> true
    }
}

@Composable
private fun ShimmerEffect() {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1)
    ) {
        repeat(10) {
            SourceCardShimmerEffect(
                modifier = Modifier.padding(horizontal = Dimens.MediumPadding1)
            )
        }

    }

}