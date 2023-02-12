package mehdi.satei.volvo.presentation.screen.details

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import mehdi.satei.volvo.BuildConfig
import mehdi.satei.volvo.R
import mehdi.satei.volvo.presentation.component.WeatherDataDisplay
import mehdi.satei.volvo.presentation.screen.details.model.WeatherDataDisplayData
import mehdi.satei.volvo.presentation.screen.details.model.WeatherDetails
import mehdi.satei.volvo.presentation.theme.VolvoTheme
import mehdi.satei.volvo.presentation.util.buildIconUrl

@Composable
fun WeatherDetailsScreen(
    id: String,
    viewModel: WeatherDetailsViewModel = hiltViewModel()
) {

    LaunchedEffect(true) {
        viewModel.getWeatherDetails(id = id)
    }

    val weatherDetails by viewModel.weatherDetails.collectAsState()

    weatherDetails?.let {
        WeatherDetailsScreen(
            weatherDetails = it,
        )
    }
}

@Composable
fun WeatherDetailsScreen(
    weatherDetails: WeatherDetails,
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Card(
                shape = RoundedCornerShape(10.dp),
                backgroundColor = MaterialTheme.colors.onSurface
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = weatherDetails.cityName,
                        modifier = Modifier.align(Alignment.End),
                        color = MaterialTheme.colors.surface,
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(
                                    data = buildIconUrl(
                                        posterUrl = BuildConfig.POSTER_URL,
                                        icon = weatherDetails.icon.dropLast(1),
                                        density = LocalContext.current.resources.displayMetrics.density,
                                        isSystemInDarkTheme = isSystemInDarkTheme()
                                    )
                                )
                                .apply(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                    scale(Scale.FIT)
                                }).build()
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp, 100.dp)
                    )
                    Text(
                        text = "${weatherDetails.temp}°C",
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.surface,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = weatherDetails.condition,
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.surface,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(weatherDetails.weatherDataDisplayList.size) { index ->
            WeatherDataDisplay(
                weatherDataDisplayData = weatherDetails.weatherDataDisplayList[index]
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun WeatherDetailsScreenPreview() {
    VolvoTheme {
        WeatherDetailsScreen(
            weatherDetails =
            WeatherDetails(
                cityName = "Stockholm",
                temp = -20,
                icon = "",
                condition = "Clear",
                weatherDataDisplayList = listOf(
                    WeatherDataDisplayData(
                        title = "Feels like",
                        value = -10,
                        unit = "°C",
                        icon = R.drawable.ic_temp,
                    ),
                    WeatherDataDisplayData(
                        title = "Feels like",
                        value = -10,
                        unit = "°C",
                        icon = R.drawable.ic_temp,
                    ),
                    WeatherDataDisplayData(
                        title = "Feels like",
                        value = -10,
                        unit = "°C",
                        icon = R.drawable.ic_temp,
                    ),
                    WeatherDataDisplayData(
                        title = "Feels like",
                        value = -10,
                        unit = "°C",
                        icon = R.drawable.ic_temp,
                    )
                )
            )
        )
    }
}