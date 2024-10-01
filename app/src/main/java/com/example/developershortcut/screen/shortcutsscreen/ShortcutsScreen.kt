package com.example.developershortcut.screen.shortcutsscreen

import android.content.Context
import android.content.Intent
import android.graphics.Color.rgb
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.developershortcut.model.ShortcutModel
import com.example.developershortcut.model.getShortcuts

@Composable
fun ShortcutsScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    onTitle: (String) -> Unit
) {
    val title = "Shortcuts"
    onTitle(title)

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(
            vertical = 24.dp,
            horizontal = 16.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(getShortcuts()) { item ->
            Shortcuts(context, item)
        }
    }
}

@Composable
fun Shortcuts(context: Context, shortcutModel: ShortcutModel) {

    Button(
        modifier = Modifier.size(150.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(color = rgb(230, 224, 233)),
            contentColor = Color.DarkGray,
        ),
        onClick = { myNavigation(context, shortcutModel.route) },
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = shortcutModel.icon),
                    contentDescription = "Favorite Icon",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(48.dp)
                )
                Text(
                    text = shortcutModel.title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    )

}

fun myNavigation(context: Context, route: String) {
    val intent = Intent(route)
    startActivity(context, intent, null)
}


@Preview(showSystemUi = true)
@Composable
fun TestOneScreenPreview() {
    ShortcutsScreen(onTitle = {})
}