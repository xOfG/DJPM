package com.example.topnews.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.topnews.Models.Article
import com.example.topnews.R
import java.util.Date


@Composable
fun ArticleBox(article: Article)
{
    Row {
        article.urlToImage?.let{
            AsyncImage(model = article.urlToImage, contentDescription = null, modifier = Modifier.height(120.dp).width(120.dp))
        }?:run {
            Image(
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp),
                painter =  painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "image article" )
        }
        Column(modifier = Modifier.padding(10.dp).weight(1f)) {
            Text(text = article.title ?: "Null", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold )
            Text(text = article.description ?: "Null")
            Text(text = article.publishedAt.toString(), fontSize = 8.sp, fontWeight = FontWeight.Light)
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun ArticleBoxPreview() {
    TopNewsTheme {
        ArticleBox()
    }
}*/

