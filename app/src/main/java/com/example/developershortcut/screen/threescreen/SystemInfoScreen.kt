package com.example.developershortcut.screen.threescreen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun SystemInfoScreen(paddingValues: PaddingValues) {

    val context = LocalContext.current
    val viewModel: SystemInfoViewModel = viewModel()

    LaunchedEffect(key1 = true) {
        viewModel.fetchSystemInfo(context)
    }

    val systemInfo by viewModel.systemInfo.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = paddingValues,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(1.dp),
    ) {
        items(systemInfo.entries.toList()) { item ->

            Info(context, viewModel, item)
        }
    }
}


@Composable
fun Info(
    context: Context,
    viewModel: SystemInfoViewModel,
    item: Map.Entry<String, String>
) {
    Card(
        modifier = Modifier
            .padding(1.dp)
            .fillMaxWidth(0.9f)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 32.dp)
        ) {
            Text(
                text = item.key,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.value
            )
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
