package com.example.developershortcut.screen.intentactionsscreen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.developershortcut.model.IntentActionModel
import com.example.developershortcut.model.getActions

@Composable
fun IntentActionsScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    onIntentActionCustom: (IntentActionModel) -> Unit,
    onTitle: (String) -> Unit
) {
    val title = "Intent Actions"
    onTitle(title)

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(
            vertical = 24.dp,
            horizontal = 16.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        content = {
            items(getActions()) { item ->
                Actions(
                    context = context,
                    intentActionModel = item
                ) {
                    onIntentActionCustom(it)
                }
            }
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Actions(
    context: Context,
    intentActionModel: IntentActionModel,
    onIntentActionCustom: (IntentActionModel) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .combinedClickable(
                onClick = { myNavigation(context, intentActionModel) },
                onLongClick = { expanded = true },
                interactionSource = remember { MutableInteractionSource() }, // Asociamos una fuente de interacción
                indication = ripple(
                    bounded = true,
                    color = Color.Gray
                ) // Agregamos ripple con un color visible
            ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = intentActionModel.icon),
                contentDescription = "Favorite Icon",
                tint = Color.DarkGray,
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = intentActionModel.name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )
            // contextual menu
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(onClick = {
                    onIntentActionCustom(intentActionModel)
                    expanded = false
                },
                    text = { Text("Custom parameters") }
                )
            }
        }
    }
}

fun myNavigation(context: Context, intentActionModel: IntentActionModel) {
    startActivity(
        context,
        Intent.createChooser(intentActionModel.intent, "Choose an email client:"),
        null
    )
}

@Preview(showBackground = true)
@Composable
fun TestOneScreenPreview() {
    IntentActionsScreen(
        onIntentActionCustom = {},
        onTitle = {}
    )
}