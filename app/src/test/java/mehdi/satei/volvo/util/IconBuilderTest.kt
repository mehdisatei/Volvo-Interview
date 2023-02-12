package mehdi.satei.volvo.util

import mehdi.satei.volvo.presentation.util.buildIconUrl
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class IconBuilderTest {


    @ParameterizedTest
    @MethodSource("provideIconBuilderArguments")
    fun iconBuilderTest(
        posterUrl: String,
        icon: String,
        density: Float,
        isSystemInDarkTheme: Boolean,
        expectedUrl: String
    ) {
        val iconUrl = buildIconUrl(
            posterUrl = posterUrl,
            icon = icon,
            density = density,
            isSystemInDarkTheme = isSystemInDarkTheme
        )

        assert(iconUrl == expectedUrl)
    }

    companion object {
        @JvmStatic
        fun provideIconBuilderArguments(): Stream<Arguments> = Stream.of(
            Arguments.of(
                "https://posterUrl.com/",
                "i",
                1f,
                false,
                "https://posterUrl.com/id@1x.png"
            ),
            Arguments.of(
                "https://posterUrl.com/",
                "i",
                2f,
                false,
                "https://posterUrl.com/id@2x.png"
            ),
            Arguments.of(
                "https://posterUrl.com/",
                "i",
                10f,
                false,
                "https://posterUrl.com/id@4x.png"
            ),
            Arguments.of(
                "https://posterUrl.com/",
                "i",
                2f,
                true,
                "https://posterUrl.com/in@2x.png"
            ),
        )
    }
}