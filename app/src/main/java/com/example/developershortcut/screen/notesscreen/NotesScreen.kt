package com.example.developershortcut.screen.notesscreen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.developershortcut.data.database.entities.NoteEntity

@Preview
@Composable
fun NotesScreenPreview() {
    NotesScreen(
        onTitle = {},
        onFabClicked = false,
        onDialogClose = {}
    )
}

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = viewModel(),
    onFabClicked: Boolean,
    onTitle: (String) -> Unit,
    onDialogClose: (Boolean) -> Unit
) {

    val title = "Notes"
    onTitle(title)

    var showCreateDialog by remember { mutableStateOf(false) }

    LaunchedEffect(onFabClicked) {
        showCreateDialog = onFabClicked
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Spacer(modifier = Modifier.height(2.dp))

        TextList(viewModel)

        if (showCreateDialog) {
            CreateNoteDialog(
                onDismiss = {
                    showCreateDialog = false
                    onDialogClose(false)
                },
                onSave = { title, body ->
                    // Save note here
                    viewModel.addNote(NoteEntity(title, body))
                    showCreateDialog = false
                    onDialogClose(false)
                }
            )
        }
    }
}

@Preview
@Composable
fun CreateNoteDialogPreview() {
    CreateNoteDialog(
        onSave = { myString1, myString2 -> },
        onDismiss = {}
    )
}

@Composable
fun CreateNoteDialog(
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                var title by remember { mutableStateOf("") }
                var body by remember { mutableStateOf("") }

                Text(text = "New Note")
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = body,
                    onValueChange = { body = it },
                    label = { Text("Message") },
                    modifier = Modifier
                        .fillMaxHeight(0.7f)
                        .fillMaxWidth(),
                    maxLines = 10
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.align(Alignment.End)) {
                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
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

@Composable
fun TextList(viewModel: NoteViewModel) {

    val textEntries by viewModel.allNoteEntity.collectAsState(initial = emptyList())

    if (textEntries.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "There isn't notes")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(1.dp),
        ) {
            items(
                items = textEntries,
                key = { it.id }
            ) { item ->
                ShowItems(item, viewModel)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowItems(
    note: NoteEntity,
    viewModel: NoteViewModel
) {
    var showDialogUpdate by remember { mutableStateOf(false) }
    var noteToUpdate by remember { mutableStateOf<NoteEntity?>(null) }
    var showDialogDelete by remember { mutableStateOf(false) }
    var noteToDelete by remember { mutableStateOf<NoteEntity?>(null) }

    Card(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(1.dp)
            .combinedClickable(
                onClick = {
                    noteToUpdate = note
                    showDialogUpdate = true
                },
                onLongClick = {
                    noteToDelete = note
                    showDialogDelete = true
                }
            )
        // .animateContentSize()
    ) {
        Column(
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 32.dp)
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

    if (showDialogUpdate) {
        noteToUpdate?.let { currentNote ->
            NoteUpdateDialog(
                note = currentNote,
                onDismiss = { showDialogUpdate = false },
                onUpdate = { title, description ->
                    // update note here
                    currentNote.title = title
                    currentNote.description = description
                    viewModel.updateNote(currentNote)
                    showDialogUpdate = false
                }
            )
        }
    }

    if (showDialogDelete) {
        noteToDelete?.let { currentNote ->
            ConfirmDeleteDialog(
                note = currentNote,
                onConfirm = {
                    // Handle the currentNote deletion here
                    viewModel.deleteNote(currentNote)
                    showDialogDelete = false
                },
                onDismiss = { showDialogDelete = false }
            )
        }
    }
}

@Composable
fun NoteUpdateDialog(
    note: NoteEntity,
    onDismiss: () -> Unit,
    onUpdate: (String, String) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                var title by remember { mutableStateOf(note.title) }
                var body by remember { mutableStateOf(note.description) }

                Text(text = "Update Note")
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    maxLines = 1
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
                Row(modifier = Modifier.align(Alignment.End)) {
                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            onUpdate(title, body)
                        }
                    ) {
                        Text("Update")
                    }
                }
            }
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
        title = { Text(text = "Confirm Deletion") },
        text = { Text("Are you sure you want to delete the note ${note.title} ?") },
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