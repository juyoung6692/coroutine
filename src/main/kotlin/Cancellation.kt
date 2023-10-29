import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    val job = launch(Dispatchers.Default) {//Dispatchers.Default 다른 스레드에서 동작
        var i = 1
        var currentTime = System.currentTimeMillis()
        while(i<=5){
            if(currentTime <= System.currentTimeMillis()){
                printWithThread("${i++} 번째 출력")
                currentTime += 1_000L
            }

            if(!isActive) throw CancellationException()
        }
    }

    delay((100))
    job.cancel()
}