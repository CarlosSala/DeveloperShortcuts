package com.example.developershortcut.screen.threescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SystemInfoScreen() {

    val context = LocalContext.current
    // Obtener el ViewModel
    // Obtener el ViewModel usando viewModel()
    val viewModel: SystemInfoViewModel = viewModel()

    // Llamar al método para obtener la información del sistema
    LaunchedEffect(key1 = true) {
        viewModel.fetchSystemInfo(context)
    }

    // Observar los cambios en systemInfo y actualizar la interfaz de usuario
    val systemInfo by viewModel.systemInfo.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Información del Sistema") },
                Modifier.background(
                    Color.Blue
                ),
                //contentColor = Color.White
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Button(onClick = { viewModel.fetchSystemInfo(context) }) {
                Text("Obtener Información del Sistema")
            }

            Text("Información del Sistema:")

            systemInfo.forEach { (key, value) ->
                Text("$key: $value")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSystemInfoScreen() {
    //SystemInfoScreen()
}


/*
@Composable
fun SystemMonitorScreen(viewModel: SystemMonitorViewModel) {

    val cpuUsage by viewModel.cpuUsage
    val ramUsage by viewModel.ramUsage
    val storageUsage by viewModel.storageUsage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("System Monitor")

        PerformanceIndicator(label = "CPU Usage", usage = cpuUsage)
        PerformanceIndicator(label = "RAM Usage", usage = ramUsage)
        PerformanceIndicator(label = "Storage Usage", usage = storageUsage)
    }
}

@Composable
fun PerformanceIndicator(label: String, usage: Float) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(label)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(
                progress = usage,
                modifier = Modifier.size(48.dp),
                strokeWidth = 6.dp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text("${(usage * 100).toInt()}%")
        }
    }
}*/
