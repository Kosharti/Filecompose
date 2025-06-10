package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.example.project.components.Link
import org.example.project.components.OptionMenu
import org.example.project.components.Title
import org.example.project.components.TransferButton
import org.example.project.components.UploaderBox
import org.example.project.utils.UploadFile
import org.example.project.utils.showNotification

@Composable
fun App() {
    val viewModel = remember { FileTransferViewModel() }
    val notifyActive by viewModel.notifyActive.collectAsState()
    val message by viewModel.message.collectAsState()
    var showFilePicker by remember { mutableStateOf(false) }

    MaterialTheme {
        AnimatedVisibility(
            visible = notifyActive,
            enter = fadeIn() + slideInVertically { -40 },
            exit = fadeOut() + slideOutVertically { -40 },
            modifier = Modifier.zIndex(2f)
        ) {
            showNotification(message)
        }

        if (showFilePicker) {
            UploadFile(
                onFileUploaded = { bytes, name ->
                    viewModel.setFile(bytes, name)
                    showFilePicker = false
                },
                onError = { e ->
                    println("Error: $e")
                    viewModel.errorMessage = e.message
                    showFilePicker = false
                }
            )
        }

        Scaffold(
            topBar = { Title() },
            bottomBar = {
                Column {
                    OptionMenu(viewModel)
                    TransferButton(viewModel)
                }
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(16.dp)
                ) {
                    UploaderBox(
                        fileName = viewModel.fileName,
                        onFileSelected = { showFilePicker = true }
                    )
                    if (viewModel.downloadLink != null) {
                        Link(viewModel)
                    }
                }
            }
        )
    }
}