package org.example.project.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinproject.composeapp.generated.resources.ic_clock
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_pencil
import org.example.project.FileTransferViewModel
import org.example.project.theme.Actor
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun OptionMenu(viewModel: FileTransferViewModel){
    Column {
        ExpirationDate(viewModel)
        Box(Modifier.padding(start = 25.dp).fillMaxWidth().background(Color(0xFFECEBF0)).height(1.dp))
        MaxDownloads(viewModel)

        Box(Modifier.padding(start = 25.dp).fillMaxWidth().background(Color(0xFFECEBF0)).height(1.dp))
        AutoDelete(viewModel)
    }
}

@Composable
fun ExpirationDate(viewModel: FileTransferViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    var tempValue by remember { mutableStateOf(viewModel.expirationDate) }

    LaunchedEffect(showDialog) {
        if (showDialog) {
            tempValue = viewModel.expirationDate
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Set expiration") },
            text = {
                Column {
                    TextField(
                        value = tempValue,
                        onValueChange = { tempValue = it},
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.updateExpirationDate(tempValue)
                    showDialog = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 15.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Image(
                painterResource(Res.drawable.ic_clock),
                "icon",
                Modifier.size(20.dp)
            )
            Text(
                text = "Expiration date",
                fontFamily = Actor(),
                fontSize = 18.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(end = 16.dp).clickable { showDialog = true }
        ) {
            Text(
                text = viewModel.expirationDate,
                fontFamily = Actor(),
                fontSize = 18.sp,
                color = Color(0xFFA1A1AA)
            )
            Image(
                painterResource(Res.drawable.ic_pencil),
                "",
                Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun MaxDownloads(viewModel: FileTransferViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    var tempValue by remember { mutableStateOf(viewModel.maxDownloads.toString()) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Max downloads") },
            text = {
                TextField(
                    value = tempValue,
                    onValueChange = { tempValue = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.updateMaxDownloads(tempValue.toIntOrNull() ?: 1)
                    showDialog = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 15.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Image(
                painterResource(Res.drawable.ic_clock),
                "icon",
                Modifier.size(20.dp)
            )
            Text(
                text = "Max downloads",
                fontFamily = Actor(),
                fontSize = 18.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(end = 16.dp).clickable { showDialog = true }
        ) {
            Text(
                text = viewModel.maxDownloads.toString(),
                fontFamily = Actor(),
                fontSize = 18.sp,
                color = Color(0xFFA1A1AA)
            )
            Image(
                painterResource(Res.drawable.ic_pencil),
                "",
                Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun AutoDelete(viewModel: FileTransferViewModel){
    var checked by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Image(
                painterResource(Res.drawable.ic_clock),
                "icon",
                Modifier.size(20.dp)
            )
            Text(
                text = "Auto delete",
                fontFamily = Actor(),
                fontSize = 18.sp
            )
        }
        Switch(
            checked = viewModel.autoDelete,
            onCheckedChange = { viewModel.autoDelete = it },
            colors = SwitchDefaults.colors(
                uncheckedTrackColor = Color(0xFFECEBF0),
                uncheckedThumbColor = Color.White,
                uncheckedBorderColor = Color.Transparent,
                checkedTrackColor = Color(0xFF526AFF),
                checkedThumbColor = Color.White,
                checkedBorderColor = Color.Transparent
            ),
            modifier = Modifier.scale(0.9f).padding(end = 16.dp)
        )
    }
}