import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.vdcast.spend_calendar.SayHelloFromCommon
import com.vdcast.spend_calendar.sayHello

fun main() {
    sayHello()

    application {
        val state = rememberWindowState().apply { size = DpSize(480.dp, 480.dp) }

        Window(
            onCloseRequest = { exitApplication() },
            state = state,
            title = "Spend Calendar"
        ) {
            SayHelloFromCommon()
        }

    }
}