import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main(): Unit = runBlocking {
    val time = measureTimeMillis {
        val job1 = async { apiCall1() }
        val job2 = async { apiCall3(job1.await()) }
    }

    //LAZY 옵션 사용시 await을 사용하면 반환 값을 계속 기다 start를 한번 사용해주면 괜찮음
    printWithThread("소요 시간: $time ms")
}

fun asyncExample2(): Unit = runBlocking {
    val job1 = async { apiCall1() }
    val job2 = async { apiCall2() }

    //job1 & job2 대기를 함께 함으로써 대기시간 단축
    printWithThread(job1.await() + job2.await())
}

fun asyncExample1(): Unit = runBlocking {
    val job = async { "hi" }

    val sum = job.await() //await: async의 결과를 반환

    printWithThread(sum)
}

suspend fun apiCall1(): Int {
    delay(5000L)
    return 3
}

suspend fun apiCall2(): Int {
    delay(2000L)
    return 3
}

suspend fun apiCall3(num: Int): Int {
    delay(5000L)
    return num + 3
}