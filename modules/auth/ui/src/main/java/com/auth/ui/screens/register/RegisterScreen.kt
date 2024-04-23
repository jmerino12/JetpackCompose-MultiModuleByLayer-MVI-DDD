package com.auth.ui.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.auth.domain.model.Register
import com.auth.ui.screens.SIDE_EFFECTS_KEY
import com.movie.theme.MovieAppThemeTypography
import com.movie.theme.MovieTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    state: RegisterContract.State,
    onEventSent: (event: RegisterContract.Event) -> Unit,
    effectFlow: Flow<RegisterContract.Effect>?,
    onNavigationRequested: (RegisterContract.Effect.Navigation) -> Unit
) {

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                RegisterContract.Effect.Navigation.Back -> {
                    onNavigationRequested(RegisterContract.Effect.Navigation.Back)
                }
            }
        }?.collect()
    }


    Scaffold(
        topBar = {
            TopAppBar(title = { /*TODO*/ }, navigationIcon = {
                IconButton(onClick = { onEventSent(RegisterContract.Event.BackButtonClicked) }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }
            })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var name by remember { mutableStateOf("Jonathan Meriño") }
            var email by remember { mutableStateOf("jmerino@merqueo.com") }
            var password by remember { mutableStateOf("123456789") }
            val keyboardController = LocalSoftwareKeyboardController.current

            when {
                state.isLoading -> CircularProgressIndicator()
                else -> {
                    Text(
                        text = "Registrarse",
                        style = MovieAppThemeTypography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nombre") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        modifier = Modifier.fillMaxWidth(fraction = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                        modifier = Modifier.fillMaxWidth(fraction = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = {
                            keyboardController?.hide()
                        }),
                        modifier = Modifier.fillMaxWidth(fraction = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextButton(onClick = {
                        onEventSent(
                            RegisterContract.Event.Register(
                                Register(
                                    name, email, password
                                ),
                            )
                        )
                    }) {
                        Text(text = "Registrarse")
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                }
            }
        }


    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    MovieTheme {
        RegisterScreen(
            onEventSent = {},
            effectFlow = null,
            onNavigationRequested = {},
            state = RegisterContract.State(
                isLoading = false,
                isError = false,
                user = null
            )
        )
    }
}