import kotlinx.coroutines.*
import java.lang.Exception

fun main() = runBlocking {
    printWithThread("START")
    printWithThread(calculateResult2())
    printWithThread("END")
}

suspend fun calculateResult2() = withContext(Dispatchers.Default) {//완전히 종료 될 떄 까지 다음 코드로 넘어가지 않음
    val num1 = async(SupervisorJob()) {
        delay(1000)
        throw Exception()
        printWithThread(coroutineContext)
        100
    }

    val num2 = async {
        delay(2000)
        printWithThread(coroutineContext)
        200
    }
}