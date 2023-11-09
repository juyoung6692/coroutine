import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val result1 = call1()

    val result2 =
        call2(result1)

    printWithThread(result2)
}

suspend fun call1(): Int =
    CoroutineScope(Dispatchers.Default).async {
        Thread.sleep(1_000L)
        100
    }.await()

suspend fun call2(num: Int) =
    CoroutineScope(Dispatchers.Default).async {
        Thread.sleep(1_000L)
        num * 2
    }.await()

//Possibly blocking call in non-blocking context could lead to thread starvation