import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Window
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.rememberAsyncImagePainter
import com.seiko.imageloader.util.DebugLogger
import com.seiko.imageloader.util.LogPriority
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        Window("") {
            CompositionLocalProvider(
                LocalImageLoader provides ImageLoader {
                    logger = object : DebugLogger(LogPriority.VERBOSE) {
                        override fun log(
                            priority: LogPriority,
                            tag: String,
                            data: Any?,
                            throwable: Throwable?,
                            message: String
                        ) {
                            super.log(priority, tag, data, throwable, message)
                            println(throwable)
                        }
                    }
                    components {
                        setupDefaultComponents(imageScope)
                    }
                },
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberAsyncImagePainter("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}
