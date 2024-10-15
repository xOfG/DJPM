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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.topnews.HomeViewModel
import com.example.topnews.Models.Article
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.Date


@Composable
fun HomeView(modifier: Modifier = Modifier) {
    val viewModel = HomeViewModel()

    val articles by viewModel.articles



    LazyColumn(modifier = Modifier.padding( top = 50.dp)){
        itemsIndexed(
            items = articles,
            ){ index, article ->
            ArticleBox(article = article)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchArticles()
    }

    }



@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    TopNewsTheme {
        HomeView()
    }
}



