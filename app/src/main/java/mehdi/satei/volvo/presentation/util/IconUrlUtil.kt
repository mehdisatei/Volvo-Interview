package mehdi.satei.volvo.presentation.util

fun buildIconUrl(
    posterUrl: String,
    icon: String,
    density: Float,
    isSystemInDarkTheme: Boolean
): String {
    return StringBuilder()
        .append(posterUrl)
        .append(icon)
        .append(if (isSystemInDarkTheme) "n" else "d")
        .append("@${density.toIconSize()}")
        .append(".png")
        .toString()
}

fun Float.toIconSize(): String {
    return when {
        this > 2f -> "4x"
        this > 1f -> "2x"
        else -> "1x"
    }
}