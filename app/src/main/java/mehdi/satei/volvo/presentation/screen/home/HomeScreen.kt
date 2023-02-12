package mehdi.satei.volvo.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.StateFlow
import mehdi.satei.volvo.presentation.component.TopBar
import mehdi.satei.volvo.presentation.navigation.Screen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navHostController: NavHostController,
    toggleTheme: () -> Unit
) {
    LaunchedEffect(true) {
        viewModel.loadWeather()
    }

    HomeScreen(
        stateFlow = viewModel.getState(),
        navHostController = navHostController,
        toggleTheme = toggleTheme
    )

}

@Composable
fun HomeScreen(
    stateFlow: StateFlow<HomeState>,
    navHostController: NavHostController,
    toggleTheme: () -> Unit
) {
    val state = stateFlow.collectAsState()
    HomeScreen(
        state = state.value,
        toggleTheme
    ) { id ->
        navHostController.navigate(route = Screen.WeatherDetails.passWeatherId(id))
    }
}

@Composable
fun HomeScreen(
    state: HomeState,
    toggleTheme: () -> Unit,
    onItemClick: (String) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            item {
                TopBar(
                    onToggle = toggleTheme
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(state.weatherList) {
                WeatherCard(
                    it,
                    onItemClicked = onItemClick
                )
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .testTag("CircularProgressIndicator")
            )
        }

        state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}