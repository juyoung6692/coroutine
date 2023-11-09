<h1>coroutine</h1>

***

~~~
root coroutine 생성:
runBlocking{}
CoroutineScope().launch{} 
~~~

<h2>rountine - coroutine</h2>

**runBlocking**: 새로운 코루틴 생성 (runBlocking 코드가 모두 끝날 때 까지 스레드가 blocking 됨)</br>

`runBlocking: 코루틴을 만들고 싶을 때 마다 사용하지 말고 프로그램에 진입할 때 최초로 사용(코루틴의 세계로 이어줌, Coroutine Scope 제공)`</br>

**launch**: 반환 값이 없는 coroutine 생성(만들어진 coroutine을 바로 실행시키지 않음)</br>
**async**: 함수 결과 반환 coroutine(`await()`: async의 결과를 가져옴)</br>
**suspend fun**: 다른 suspend 함수 호출</br>
ex) yield() - suspend 함수, 스레드 양보
delay() - 특정 시간 동안 멈추고 다른 코루틴에 넘김

routine | coroutine
--------- | ---------
시작되면 끝날 때 까지 멈추지 않음 | 중단되었다가 재개 가능
한 번 끝나면 루틴 내의 정보 사라짐 | 중단되더라고 재개시 사용할 수 있으므로 정보가 사라지지 않음

*VM options: -Dkotlinx.coroutines.debug (어느 coroutine에서 실행하는지 디버그 가능)


<h2>Thread - coroutine</h2>

**Thread** 

+ 프로세스에 소속되어 여러 코드를 동시에 실행
+ 프로세스가 있어야만 존재
+ 스레드가 프로세스를 바꿀 수 없음
+  `context switching`: Heap 메모리를 공유하고 stack만 교체 되므로 프로세스 보다 비용이 적음(프로세스: 메모리 전체 갈아 끼움)
+ 선점형


**Coroutine**

+ 코루틴의 코드가 실행 되려면 스레드가 있어야함
+ 중단 되었다가 재개 될때 다른 스레드에 배정 될 수 있음
+ `context switching`: 동일 스레드에서 coroutine이 실행되면 메모리 전부를 공유하므로 스레드 보다 비용이 적음</br>(하나의 스레드에서 동시성 확보 - 아주 빠르게 작업이 전환 됨)
+ 비선점형

<h2>코루틴 취소</h2>

- kotlinx.coroutines 패키지에 있는 함수 사용시 취소 가능</br>
ex)delay(), yield()
- 코루틴 스스로 본인의 상태를 확인해 CancellationException 던지기</br>

` 코루틴 내부에서 isActive로 본인의 상태 확인 가능 `


<b>async</b> 
- root coroutine 일 경우 예외 발생 시 예외 출력 않고 await()사용시 예외 확인 가능
</br>

<b>launch</b> 
- 에외 발생시 바로 예외 출력 및 코루틴 종료


CoroutineExceptionHandler 
- 코루틴에서 발생한 exception을 처리
- launch에만 적용 가능
- 부모 코루틴 존재시 동작 X
`자식 코루틴의 예외는 부모에게 전파됨(SuperviserJob() 사용 시 예외 전파 막음)`

  
CancellationException | 그외 Exception
--------- | ---------
취소로 간주 | 예외로 간주
부모에게 예외 전파 X | 부모에게 예외 전파 O
~~~
Coroutines.LAZY: 시작신호를 줄 때 까지 코루틴 실행 대기</br>
Dispatchers.Default: 다른 스레드에서 코루틴 실행
~~~

Structured Concurrency
- 자식 코루틴에서 예외 발생시 부모에게 전파, 또 그 자식 코루틴에 전파
- 코루틴이 유실되거나 누수되지 않도록 보장

Coroutine Scope
- Coroutine 이 탄생할 수 있는 영역
- Coroutine context 데이터를 보관
`coroutine context: 코루틴과 관련된 데이터를 보관 (CoroutineExceptionHandler, 코루틴 이름, coroutine dispatcher ...)`

Coroutine Dispatcher
- 코루틴을 스레드에 배정하는 역할

Dispatcher | *
--------- | ---------
Dispatchers.Default</br>(default) | - 가장 기본적인 디스패처</br>- CPU 자원을 많이 쓸 때 권장
Dispatchers.Default | - IO 작업에 최적화
Dispatchers.Main | - UI 관련 의존성 필요

~~~
Executor Service를 asCoroutineDispatcher() 함수 활용하여 dispatcher로 
~~~

Suspending Function
- 코루틴이 중지 되었다가 재개 될 수 있는 지점

~~~
* suspend 함수 *
 coroutineScope
  - 추가적인 코루틴을 만들어주고, 주어진 함수 블록이 바로 실행된다.
  - 만들어진 코루틴이 완료되면 다음 코드로 넘어간다.
 withContext
  - context 변화를 주는 기능 추적으로 있음
~~~