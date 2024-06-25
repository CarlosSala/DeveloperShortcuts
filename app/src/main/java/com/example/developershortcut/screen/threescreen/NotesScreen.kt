package com.example.developershortcut.screen.threescreen

import android.content.Context
import android.view.View.OnLongClickListener
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.developershortcut.data.database.entities.NoteEntity
import com.example.developershortcut.screen.fourscreen.SystemInfoViewModel

@Composable
fun NoteScreen(paddingValues: PaddingValues) {

    var showDialog by remember { mutableStateOf(false) }

    val viewModel: NoteViewModel = viewModel()

    Scaffold(
        modifier = Modifier.padding(paddingValues),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true }
            ) {
                Text("+")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // TextEntryInput(viewModel)
            Spacer(modifier = Modifier.height(16.dp))
            TextEntriesList(viewModel)

            if (showDialog) {
                NoteDialog(onDismiss = { showDialog = false }, onSave = { title, body ->
                    // Save note here
                    viewModel.addNote(noteEntity = NoteEntity(title, body))
                    // title = ""
                    showDialog = false
                })
            }
        }
    }
}

@Composable
fun NoteDialog(onDismiss: () -> Unit, onSave: (String, String) -> Unit) {

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            shape = MaterialTheme.shapes.medium,
            // color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                var title by remember { mutableStateOf("") }
                var body by remember { mutableStateOf("") }

                Text(text = "New Note")
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = body,
                    onValueChange = { body = it },
                    label = { Text("Note") },
                    modifier = Modifier
                        .fillMaxHeight(0.7f)
                        .fillMaxWidth(),
                    maxLines = 10
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            onSave(title, body)
                        }
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}

/*@Composable
fun TextEntryInput(viewModel: NoteViewModel) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Enter text") },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                //  viewModel.addNote(text)
                text = ""
            }
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}*/

@Composable
fun TextEntriesList(viewModel: NoteViewModel) {

    val textEntries by viewModel.allNoteEntity.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(1.dp),
    ) {
        items(textEntries) { item ->

            ShowItems(item, viewModel)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowItems(
    note: NoteEntity,
    viewModel: NoteViewModel
) {
    var longClickedItem by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var noteToDelete by remember { mutableStateOf<NoteEntity?>(null) }

    Card(
        modifier = Modifier
            .padding(1.dp)
            .fillMaxWidth(0.95f)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 32.dp)
                .combinedClickable(
                    onClick = { },
                    onLongClick = {
                        noteToDelete = note
                        showDialog = true
                    })
            // onLongClick = { viewModel.deleteNote(note)})
            // viewModel.deleteNote(note)
        ) {
            Text(
                text = note.title,
                modifier = Modifier.padding(1.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = note.description,
                modifier = Modifier.padding(1.dp)
            )
        }
    }
    if (showDialog) {
        noteToDelete?.let { note ->
            ConfirmDeleteDialog(
                note = note,
                onConfirm = {
                    // Handle the note deletion here
                    viewModel.deleteNote(note)
                    showDialog = false
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}

@Composable
fun ConfirmDeleteDialog(
    note: NoteEntity,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Confirm Deletion")
        },
        text = {
            Text("Are you sure you want to delete the note \"$note\"?")
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun ConfirmDewleteDialog(
    //   onConfirm: () -> Unit
//    onDismiss: () -> Unit
) {
    /* AlertDialog(
         onDismissRequest = onDismiss,
         title = {
             Text(text = "Confirm Deletion")
         },
         text = {
             Text("Are you sure you want to delete the note \"$note\"?")
         },
         confirmButton = {
             Button(
                 onClick = onConfirm
             ) {
                 Text("Confirm")
             }
         },
         dismissButton = {
             Button(
                 onClick = onDismiss
             ) {
                 Text("Cancel")
             }
         },
         properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
     )*/
}