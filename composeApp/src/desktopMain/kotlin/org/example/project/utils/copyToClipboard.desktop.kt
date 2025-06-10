package org.example.project.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.example.project.FileTransferViewModel
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

@Composable
actual fun copyToClipboard(text: String) {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    val stringSelection = StringSelection(text)
    clipboard.setContents(stringSelection, null)

    val viewModel = remember { FileTransferViewModel() }
    viewModel.showNotification("Link copied")
}