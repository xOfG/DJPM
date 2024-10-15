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

    var articles by remember { mutableStateOf(listOf<Article>()) }



    LazyColumn(modifier = Modifier.padding( top = 50.dp)){
        itemsIndexed(
            items = articles,
            ){ index, article ->
            ArticleBox(article = article)
        }
    }

    LaunchedEffect(Unit){
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("\n" +
                    "https://newsapi.org/v2/everything?q=tesla&from=2024-09-15&sortBy=publishedAt&apiKey=134b05c86a4649558ffda9f98b8ca6c8")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val articlesResult = arrayListOf<Article>()
                    val result = response.body!!.string()
                    val jsonResult = JSONObject(result)
                    val status = jsonResult.getString("status")
                    if (status == "ok") {
                        val articlesJson = jsonResult.getJSONArray("articles")
                        for (index in 0 until articlesJson.length()) {
                            val articleJson = articlesJson.getJSONObject(index)
                            val article = Article(
                                title = articleJson.getString("title"),
                                description = articleJson.getString("description"),
                                urlToImage = articleJson.getString("urlToImage"),
                                url = articleJson.getString("url"),
                                publishedAt = Date())
                            articlesResult.add(article)
                        }
                    }
                    articles = articlesResult

                }
            }
        })
    }
    }



@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    TopNewsTheme {
        HomeView()
    }
}



