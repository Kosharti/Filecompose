package org.example.project.utils

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun showNotification(message: String) {
    val context = LocalContext.current
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}