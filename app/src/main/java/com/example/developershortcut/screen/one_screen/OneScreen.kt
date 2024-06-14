package com.example.developershortcut.screen.one_screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.developershortcut.model.ActionModel
import com.example.developershortcut.model.actions

@Composable
fun OneScreen(context: Context, paddingValues: PaddingValues) {

    LazyVerticalGrid(
        contentPadding = paddingValues,
        // columns = GridCells.Fixed(2)
        columns = GridCells.Adaptive(150.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)

    ) {
        items(actions()) { item ->

            MyActions(context, item)
        }
    }
}


@Composable
fun MyActions(context: Context, actionModel: ActionModel) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {

            IconButton(modifier = Modifier.fillMaxWidth(),
                onClick = { myNavigation(context, actionModel.route) }) {
                Icon(
                    painter = painterResource(id = actionModel.icon),
                    contentDescription = "Favorite Icon",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(48.dp)
                )
            }
            Text(
                text = actionModel.title,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )

        }
    }
}

fun myNavigation(context: Context, route: String) {

    val intent = Intent(route)
    startActivity(context, intent, null)
}


@Preview(showBackground = true)
@Composable
fun TestOneScreenPreview() {

}