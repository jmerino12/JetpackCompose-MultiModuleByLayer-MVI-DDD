package com.auth.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movie.theme.MovieTheme

@Composable
fun RegisterScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(value = "", onValueChange = {}, leadingIcon = {
            Icon(
                Icons.Default.Email,
                ""
            )
        }, placeholder = {
            Text(text = "Email")
        })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = "", onValueChange = {}, leadingIcon = {
            Icon(
                Icons.Default.AccountCircle,
                ""
            )
        }, placeholder = {
            Text(text = "Email")
        })

        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = "", onValueChange = {}, leadingIcon = {
            Icon(
                Icons.Filled.Warning,
                ""
            )
        }, placeholder = {
            Text(text = "Contraseña")
        })
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Registrarse")
        }

    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    MovieTheme {
        RegisterScreen()
    }
}