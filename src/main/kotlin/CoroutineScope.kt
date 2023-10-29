import kotlinx.coroutines.*

fun d(): Unit = runBlocking {
    val job = CoroutineScope(Dispatchers.Default).async {
        printWithThread("job 1")
    }

    printWithThread("job 2")
}

fun main(): Unit = runBlocking {
    //자식스레드의 경우 부모에 예외를 전파하기 때문에 예외 출력
    val job = async {
        throw IllegalArgumentException()
    }

    delay(500)
    printWithThread("job 2")
}

fun example2Exception(): Unit = runBlocking {
    //예외 출력 함
    val job = CoroutineScope(Dispatchers.Default).launch {
        throw IllegalArgumentException()
    }

    delay(500)
    printWithThread("job 2")
}

fun example1Exception(): Unit = runBlocking {
    //예외 출력 안함
    val job = CoroutineScope(Dispatchers.Default).async {
        throw IllegalArgumentException()
    }

    delay(500)
    job.await()//main 스레드에서 예외 출력
    printWithThread("job 2")
}