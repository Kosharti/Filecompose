package org.example.project

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.example.project.utils.getBaseUrl

class FileTransferApi {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
    }
    private val baseUrl = getBaseUrl()

    suspend fun uploadFile(request: FileTransferRequest): FileTransferResponse {

        println("DEBUG: Sending to server:")
        println("DEBUG: - expires: ${request.expiration}")
        println("DEBUG: - maxDownloads: ${request.maxDownloads}")
        println("DEBUG: - autoDelete: ${request.autoDelete}")

        val formData = formData {
            append("file", request.fileContent, Headers.build {
                append(HttpHeaders.ContentDisposition, "filename=\"${request.fileName}\"")
            })
            append("expires", request.expiration)
            append("maxDownloads", request.maxDownloads.toString())
            append("autoDelete", (!request.autoDelete).toString())
        }

        val response = client.post("$baseUrl/api/upload") {
            setBody(MultiPartFormDataContent(formData))
            header(HttpHeaders.Accept, "application/json")
        }

        val responseText = response.bodyAsText()

        return Json.decodeFromString<FileTransferResponse>(responseText)
    }
}

@Serializable
data class FileTransferRequest(
    val fileContent: ByteArray,
    val fileName: String,
    val expiration: String,
    val maxDownloads: Int,
    val autoDelete: Boolean
)

@Serializable
data class FileTransferResponse(
    val success: Boolean,
    val link: String,
    val filename: String,
    val expires: String,
    val maxDownloads: String,
    val message: String
)