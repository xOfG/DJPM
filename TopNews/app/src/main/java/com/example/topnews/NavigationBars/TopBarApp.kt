package com.example.topnews.NavigationBars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.topnews.ui.theme.PurpleGrey40


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBarApp(){
        CenterAlignedTopAppBar(
            colors = topAppBarColors(
                containerColor = PurpleGrey40,
                titleContentColor = Color.White
            ),
            title = {
                Text("TopNews",
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = { /* TODO */ }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back Arrow",
                        tint = Color.White,
                    )
                }
            },
            actions = {
                IconButton(onClick = { /* TODO */ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Actions Menu",
                        tint = Color.White
                    )
                }
            },

            )
    }

/*-------------------------------------------------------------------------------*/

@Composable
@Preview(showBackground = true)
fun TopBarPreview(){
    topBarApp()
}