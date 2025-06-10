package org.example.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class FileTransferViewModel : ViewModel() {
    var fileName by mutableStateOf<String?>(null)
    var downloadLink by mutableStateOf<String?>(null)
    var errorMessage by mutableStateOf<String?>(null)
    var fileContent by mutableStateOf<ByteArray?>(null)

    var isLoading by mutableStateOf(false)
    var expirationDate by mutableStateOf("7d")
    var maxDownloads by mutableStateOf(1)
    var autoDelete by mutableStateOf(false)

    private val api = FileTransferApi()

    fun setFile(fileContent: ByteArray, fileName: String) {
        this.fileContent = fileContent
        this.fileName = fileName
        this.errorMessage = null
    }

    fun uploadFile() {
        if (fileContent == null || fileName == null) {
            errorMessage = "No file selected"
            return
        }

        isLoading = true
        errorMessage = null

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = FileTransferRequest(
                    fileContent = fileContent!!,
                    fileName = fileName!!,
                    expiration = expirationDate,
                    maxDownloads = maxDownloads,
                    autoDelete = autoDelete
                )

                val response = api.uploadFile(request)

                withContext(Dispatchers.Main) {
                    downloadLink = response.link
                }
            } catch (e: Exception) {
                errorMessage = e.message ?: "Failed to upload file"
            } finally {
                isLoading = false
            }
        }
    }

    fun updateExpirationDate(newValue: String) {
        expirationDate = when {
            newValue.endsWith("min") -> newValue
            newValue.endsWith("h") -> newValue
            newValue.endsWith("d") -> newValue
            newValue.endsWith("w") -> newValue
            else -> "${newValue}d"
        }
    }

    fun updateMaxDownloads(newValue: Int) {
        maxDownloads = newValue.coerceAtLeast(1)
    }

    val notifyActive = MutableStateFlow(false)
    val message = MutableStateFlow("")

    fun showNotification(text: String) {
        message.value = text
        notifyActive.value = true

        viewModelScope.launch {
            delay(4000)
            notifyActive.value = false
        }
    }
}