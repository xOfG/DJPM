package com.example.topnews.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.topnews.Models.Article
import java.util.Date


@Composable
fun HomeView(modifier: Modifier = Modifier) {

    var articles = arrayListOf(
        Article(
            "title1",
            "description",
            "https://m.media-amazon.com/images/S/pv-target-images/dbf6812f59e5080cf420f1056bfceb66f7d6a43a8df19ace503ea70596afc0ff.jpg",
            "url",
            Date()
        )

    )

    LazyColumn(modifier = Modifier.padding( top = 50.dp)){
        itemsIndexed(
            items = articles,
            ){ index, article ->
            ArticleBox(article = article)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    TopNewsTheme {
        HomeView()
    }
}



