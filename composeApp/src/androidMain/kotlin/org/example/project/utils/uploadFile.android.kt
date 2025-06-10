package org.example.project.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import java.io.InputStream

@Composable
actual fun UploadFile(
    onFileUploaded: (ByteArray, String) -> Unit,
    onError: (Throwable) -> Unit
) {
    val context = LocalContext.current
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                var inputStream: InputStream? = null
                try {
                    val fileName = getFileName(context, it) ?: "unknown_file"
                    inputStream = context.contentResolver.openInputStream(it)
                    val fileBytes = inputStream?.readBytes() ?: byteArrayOf()
                    onFileUploaded(fileBytes, fileName)
                } catch (e: Exception) {
                    onError(e)
                } finally {
                    inputStream?.close()
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        filePickerLauncher.launch("*/*")
    }
}

@SuppressLint("Range")
private fun getFileName(context: Context, uri: Uri): String? {
    var name: String? = null
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        it.moveToFirst()
        name = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
    }
    return name ?: uri.path?.substringAfterLast('/')
}