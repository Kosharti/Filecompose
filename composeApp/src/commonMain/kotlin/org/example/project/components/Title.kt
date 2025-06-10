package org.example.project.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.theme.RammettoOne
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun Title() {
    Box(
        modifier = Modifier.fillMaxWidth().padding(top = 30.dp, start = 16.dp)
    ){
        Text(
            text = "File Transfer",
            fontFamily = RammettoOne(),
            fontSize = 25.sp
        )
    }
}