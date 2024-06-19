package com.example.developershortcut.screen.twoscreen

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.developershortcut.model.IntentActionModel
import com.example.developershortcut.model.getActions

@Composable
fun TwoScreen(context: Context, paddingValues: PaddingValues) {

    // Memoriza la lista para evitar recomposiciones innecesarias
    val actions = remember { getActions() }

    LazyVerticalGrid(
        // columns = GridCells.Adaptive(150.dp),
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(16.dp),
        contentPadding = paddingValues,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(getActions()) { item ->

                // Log.d("MyLazyVerticalGrid", "Item in list: $item")
                MyActions(context = context, intentActionModel = item)
            }
        }
    )
}


@Composable
fun MyActions(context: Context, intentActionModel: IntentActionModel) {

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
                onClick = {
                    myNavigation(
                        context,
                        intentActionModel
                    )
                }) {
                Icon(
                    painter = painterResource(id = intentActionModel.icon),
                    contentDescription = "Favorite Icon",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(48.dp)
                )
            }
            Text(
                text = intentActionModel.name,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )

        }
    }
}

fun myNavigation(context: Context, intentActionModel: IntentActionModel) {

    /*   val intent = Intent(Intent.ACTION_SEND).apply {
           type = "message/rfc822"
           putExtra(Intent.EXTRA_EMAIL, arrayOf("example@example.com"))
           putExtra(Intent.EXTRA_SUBJECT, "Subject")
           putExtra(Intent.EXTRA_TEXT, "Body")
       }*/
    startActivity(
        context,
        Intent.createChooser(intentActionModel.intent, "Choose an email client:"),
        null
    )
}

@Preview(showBackground = true)
@Composable
fun TestOneScreenPreview() {
}