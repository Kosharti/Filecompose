package org.example.project.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_upload
import org.example.project.theme.Actor
import org.jetbrains.compose.resources.painterResource

@Composable
fun UploaderBox(
    modifier: Modifier = Modifier,
    fileName: String? = null,
    onFileSelected: () -> Unit,
    isDragActive: Boolean = false
) {
    Box(
        modifier = modifier
            .background(
                if (isDragActive) Color(0xFFE0E5FF) else Color(0xFFF8F9FF),
                RoundedCornerShape(20.dp)
            )
            .clickable(onClick = onFileSelected)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            drawRoundRect(
                color = Color(0xFF526AFF),
                size = size,
                cornerRadius = CornerRadius(20.dp.toPx()),
                style = Stroke(
                    width = 1.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f))
                )
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center),
        ) {
            if (fileName != null) {
                Text(
                    text = fileName,
                    fontFamily = Actor(),
                    fontSize = 18.sp,
                    color = Color(0xFF526AFF),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            } else {
                Image(
                    painter = painterResource(Res.drawable.ic_upload),
                    "ic_upload",
                    Modifier
                        .padding(18.dp)
                        .size(60.dp)
                )
            }

            Text(
                text = if (fileName != null) "File selected" else "Upload a file",
                fontFamily = Actor(),
                fontSize = 24.sp,
                color = Color(0xFF526AFF)
            )
            Text(
                text = if (fileName != null) "Click to change file" else "Drag and drop or browse to\nchoose a file",
                fontFamily = Actor(),
                fontSize = 17.sp,
                color = Color(0xFF526AFF),
                textAlign = TextAlign.Center
            )
        }
    }
}