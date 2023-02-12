package mehdi.satei.volvo.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mehdi.satei.volvo.R
import mehdi.satei.volvo.presentation.screen.details.model.WeatherDataDisplayData
import mehdi.satei.volvo.presentation.theme.VolvoTheme

@Composable
fun WeatherDataDisplay(
    weatherDataDisplayData: WeatherDataDisplayData,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .size(80.dp, 60.dp)
            .clip(RoundedCornerShape(8.dp)),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = weatherDataDisplayData.title,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.surface
            )
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = weatherDataDisplayData.icon),
                    contentDescription = null,
                    tint = MaterialTheme.colors.surface,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${weatherDataDisplayData.value} ${weatherDataDisplayData.unit}",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.surface
                )
            }
        }

    }

}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun WeatherDataDisplayPreview() {
    VolvoTheme {
        WeatherDataDisplay(
            WeatherDataDisplayData(
                title = "Feels like",
                value = -10,
                unit = "°C",
                icon = R.drawable.ic_temp,
            )
        )
    }
}