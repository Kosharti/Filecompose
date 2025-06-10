package org.example.project.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import org.example.project.theme.Actor

@Composable
actual fun showNotification(message: String) {
    Box(
        modifier = Modifier.zIndex(2f)
            .padding(horizontal = 24.dp, vertical = 48.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF038734), RoundedCornerShape(15.dp))
                .padding(16.dp)
        ) {
            Text(
                message,
                fontFamily = Actor(),
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}