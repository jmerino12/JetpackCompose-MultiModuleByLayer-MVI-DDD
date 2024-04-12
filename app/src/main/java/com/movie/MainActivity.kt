package com.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.auth.ui.screens.login.LoginContract
import com.auth.ui.screens.login.LoginScreen
import com.auth.ui.screens.login.LoginViewModel
import com.auth.ui.screens.register.RegisterContract
import com.auth.ui.screens.register.RegisterScreen
import com.auth.ui.screens.register.RegisterViewModel
import com.movie.ui.theme.MovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    MovieNavGraph(navHostController = navController)

                }
            }
        }
    }
}

@Composable
fun MovieNavGraph(
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = "login") {
        composable("login") {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                state = viewModel.viewState.value,
                effectFlow = viewModel.effect,
                onEventSent = { event -> viewModel.setEvent(event) },
                onNavigationRequested = { navigationEffect ->
                    if (navigationEffect is LoginContract.Effect.Navigation.ToRegister) {
                        navHostController.navigate("register")
                    }
                }
            )
        }

        composable("register") {
            val viewModel = hiltViewModel<RegisterViewModel>()
            RegisterScreen(
                state = viewModel.viewState.value,
                onEventSent = { event -> viewModel.setEvent(event) },
                effectFlow = viewModel.effect,
                onNavigationRequested = { navigationEffect ->
                    if (navigationEffect is RegisterContract.Effect.Navigation.Back) {
                        navHostController.popBackStack()
                    }
                }
            )
        }
    }
}

