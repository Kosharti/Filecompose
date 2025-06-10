package org.example.project.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import kotlinproject.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font
import kotlinproject.composeapp.generated.resources.rammetto_one_regular
import kotlinproject.composeapp.generated.resources.actor_regular

@Composable
fun RammettoOne() = FontFamily(
    Font(Res.font.rammetto_one_regular)
)

@Composable
fun Actor() = FontFamily(
    Font(Res.font.actor_regular)
)