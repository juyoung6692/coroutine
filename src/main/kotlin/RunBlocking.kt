import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main() = runBlocking {
    printWithThread("START")
    launch {
        sumNum()
    }
    printWithThread("END")
}

suspend fun sumNum() {
    val num1 = 1
    val num2 = 2
    yield() //스레드 양보
    printWithThread("${num1 + num2}")
}

fun printWithThread(str: Any){
    println("${Thread.currentThread().name}: $str")
}