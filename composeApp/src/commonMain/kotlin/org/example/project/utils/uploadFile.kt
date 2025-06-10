package org.example.project.utils

import androidx.compose.runtime.Composable

@Composable
expect fun UploadFile(
    onFileUploaded: (ByteArray, String) -> Unit,
    onError: (Throwable) -> Unit
)