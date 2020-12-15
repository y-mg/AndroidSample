package com.ymg.android.paging.util.event



// 이벤트를 나타내는 LiveData 를 통해 노출되는 데이터의 래퍼로 사용됨.
open class Event<out T>(val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        // 이벤트가 이미 처리 되었으면 null
        // 아니라면 이벤트가 처리되었다는 것을 표시 후 값을 반환
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}