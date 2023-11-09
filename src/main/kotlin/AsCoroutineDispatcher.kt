import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

fun main(){
    printWithThread("Main Thread")
    val threadPool = Executors.newSingleThreadExecutor() //하나의 스레드를 가진 풀 생성

    CoroutineScope(threadPool.asCoroutineDispatcher()).launch {
        printWithThread("Executor Service as Dispatcher")
    }
}
