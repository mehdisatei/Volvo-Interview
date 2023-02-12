package mehdi.satei.volvo.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mehdi.satei.volvo.R
import mehdi.satei.volvo.presentation.theme.VolvoTheme

@Composable
fun TopBar(onToggle: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(id = R.string.top_bar_title),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.surface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.top_bar_body),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.surface
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 24.dp, 36.dp, 0.dp),
            horizontalArrangement = Arrangement.End
        ) {
            ThemeSwitch(onToggle = { onToggle() })
        }
    }
}

@Composable
fun ThemeSwitch(onToggle: () -> Unit) {

    val icon = if (isSystemInDarkTheme())
        painterResource(id = R.drawable.ic_light_off)
    else
        painterResource(id = R.drawable.ic_light_on)

    Icon(
        painter = icon,
        contentDescription = null,
        modifier = Modifier
            .size(24.dp, 24.dp)
            .clickable(onClick = onToggle),
        tint = colorResource(id = R.color.text)
    )
}

@Preview
@Composable
fun PreviewTopBar() {
    VolvoTheme {
        TopBar {

        }
    }
}

@Preview
@Composable
fun PreviewThemeSwitch() {
    VolvoTheme {
        ThemeSwitch {
        }
    }
}