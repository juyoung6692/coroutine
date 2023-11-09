import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    printWithThread("START")
    printWithThread(calculateResult())
    printWithThread("END")
}

suspend fun calculateResult() = coroutineScope {//완전히 종료 될 떄 까지 다음 코드로 넘어가지 않음
    val num1 = async {
        delay(1000)
        100
    }

    val num2 = async {
        delay(2000)
        200
    }

    num1.await() + num2.await()
}