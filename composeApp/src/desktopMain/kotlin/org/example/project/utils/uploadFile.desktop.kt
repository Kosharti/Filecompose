package org.example.project.utils

import androidx.compose.runtime.Composable
import javax.swing.JFileChooser

@Composable
actual fun UploadFile(
    onFileUploaded: (ByteArray, String) -> Unit,
    onError: (Throwable) -> Unit
) {
    try {
        val fileChooser = JFileChooser().apply {
            dialogTitle = "Выберите файл для загрузки"
            isAcceptAllFileFilterUsed = true
        }

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            val file = fileChooser.selectedFile
            println("DEBUG: Selected file: ${file.absolutePath}")
            val bytes = file.readBytes()
            println("DEBUG: File size: ${bytes.size} bytes")
            onFileUploaded(bytes, file.name)
        } else {
            println("DEBUG: File selection cancelled")
        }
    } catch (e: Exception) {
        println("DEBUG: File selection error: ${e.message}")
        onError(e)
    }
}