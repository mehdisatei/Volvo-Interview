package mehdi.satei.volvo.presentation.screen.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import mehdi.satei.volvo.BuildConfig
import mehdi.satei.volvo.presentation.screen.home.model.WeatherSummary
import mehdi.satei.volvo.presentation.theme.VolvoTheme
import mehdi.satei.volvo.presentation.util.buildIconUrl

@Composable
fun WeatherCard(
    weatherSummary: WeatherSummary,
    onItemClicked: (id: String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { onItemClicked(weatherSummary.cityName) }),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Image(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .clip(RoundedCornerShape(16.dp)),
                painter = rememberAsyncImagePainter(
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(
                            data = buildIconUrl(
                                posterUrl = BuildConfig.POSTER_URL,
                                icon = weatherSummary.icon.dropLast(1),
                                density = LocalContext.current.resources.displayMetrics.density,
                                isSystemInDarkTheme = isSystemInDarkTheme()
                            )
                        )
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                            scale(Scale.FILL)
                        }).build()
                ),
                alignment = Alignment.CenterStart,
                contentDescription = "",
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = weatherSummary.cityName,
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = buildString {
                        append("H: ${weatherSummary.maxTemp}°")
                        append(" | ")
                        append("L: ${weatherSummary.minTemp}°")
                    },
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colors.surface,
                    style = MaterialTheme.typography.caption
                )

                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = weatherSummary.condition,
                        color = MaterialTheme.colors.surface,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = weatherSummary.temp.toString(),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = "°C",
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun WeatherCardPreview() {
    VolvoTheme() {
        WeatherCard(
            weatherSummary = WeatherSummary(
                cityName = "Stockholm",
                temp = 0,
                minTemp = -1,
                maxTemp = 2,
                condition = "Windy",
                icon = ""
            ),
            onItemClicked = {}
        )
    }
}