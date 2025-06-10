package org.example.project.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.FileTransferViewModel
import org.example.project.theme.Actor
import org.example.project.utils.copyToClipboard

@Composable
fun Link(viewModel: FileTransferViewModel) {
    val link = viewModel.downloadLink ?: "*Ссылка*"
    var clicked by remember { mutableStateOf(false) }
    if (clicked) {
        copyToClipboard(link)
        viewModel.showNotification("Link copied")
        clicked = false
    }
    Column {
        Text(
            text = "Your Link",
            fontFamily = Actor(),
            fontSize = 25.sp,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .clickable {
                    clicked = true
                }
                .border(width = 1.dp, color = Color(0xFF526AFF), shape = RoundedCornerShape(20.dp))
                .padding(vertical = 12.dp, horizontal = 18.dp)

        ){
            Row {
                Text(
                    link,
                    fontFamily = Actor(),
                    fontSize = 25.sp,
                    color = if (!clicked) Color(0xFF526AFF) else Color.Green
                )
            }
        }
    }
}
