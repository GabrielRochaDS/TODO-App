package com.example.auxilyarisolution.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdUnits
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.TODOTheme

@Composable
fun ConfirmatorDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    icon: @Composable () -> Unit,
    principalText: @Composable () -> Unit,
    secondaryText: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {


    AlertDialog(
        modifier = modifier,
        icon = {
            icon()
        },
        title = {
            principalText()
        },
        text = {
            secondaryText()
        },
        onDismissRequest = {

                onDismissRequest()

        },
        confirmButton = {
            TextButton(
                onClick = { onConfirmation() },
            ) {
                Text("Confirmar")
            }

        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancelar")
            }

        }
    )

}

@Preview(showBackground = true)
@Composable
fun ConfirmatorDialogPreview() {
    TODOTheme {
        ConfirmatorDialog(
            onDismissRequest = { /*TODO*/ },
            onConfirmation = {/*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Filled.AdUnits,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )
            },
            principalText = {
                Text(
                    text = "Caution!",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    fontSize = 20.sp
                )
            },
            secondaryText = {
                Text(
                    text = "Are you sure you want to delete?",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Justify
                )
            },
        )
    }
}