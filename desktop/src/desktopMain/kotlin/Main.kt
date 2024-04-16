import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.vdcast.spendcalendar.root.RootViewModel
import com.vdcast.spendcalendar.root.compose.RootScreen
import com.vdcast.spendcalendar.sayHello

fun main() {
    sayHello()

    application {
        val state = rememberWindowState().apply { size = DpSize(480.dp, 480.dp) }

        Window(
            onCloseRequest = { exitApplication() },
            state = state,
            title = "Spend Calendar"
        ) {
            RootScreen(RootViewModel())
//            SayHelloFromCommon()
        }

    }
}