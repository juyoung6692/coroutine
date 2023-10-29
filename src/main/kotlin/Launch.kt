import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val job1 = launch {
        delay(1000)
        printWithThread("JOB 1")
    }

    job1.join()// job1 coroutine이 완전히 끝날때까지 대기

    val job2 = launch {
        delay(1000)
        printWithThread("JOB 2")
    }
}

fun example2(): Unit = runBlocking {
    val job = launch {
        (1..5).forEach{
            printWithThread(it)
            delay(500)
        }
    }

    delay(1_000L)
    job.cancel()
}

fun example1(): Unit = runBlocking {
    val job = launch(start = CoroutineStart.LAZY) { //명확 한 시작 신호를 줄 때 까지 대기 ..LAZY
        printWithThread("Hello")
    }

    delay(1_000L)
    job.start()
}