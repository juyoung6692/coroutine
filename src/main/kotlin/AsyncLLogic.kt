import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AsyncLLogic {
    private val scope = CoroutineScope(Dispatchers.Default)

    fun doSomething(){
        scope.launch {
            coroutineContext
        }
    }

    fun destroy(){
        scope.cancel()
    }
}