package com.example.topnews.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.topnews.Models.Article
import com.example.topnews.R
import com.example.topnews.toYYYYMMDD
import java.util.Date


@Composable
fun ArticleBox(modifier: Modifier = Modifier, article: Article) {
    Row(modifier = modifier) {
        article.urlToImage?.let {
            AsyncImage(
                model = it,
                contentDescription = "image article",
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(6.dp),
                contentScale = ContentScale.Crop
            )
        } ?: run {
            Image(
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(6.dp),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "image article",
                contentScale = ContentScale.Crop,
            )
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = article.title?: "",
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,)
            Text(text = article.description?: "",
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,)
            Text(text = article.publishedAt?.toYYYYMMDD()?:"")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowArticlePreview() {
    TopNewsTheme {
        ArticleBox(article = Article(
            "Title",
            "description",
            null,
            "url",
            Date()
        ))
    }
}

