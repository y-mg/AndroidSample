package com.ymg.android.mvvm.util.event

import androidx.lifecycle.Observer



// View 옵저버에서 매번 사용되는 "it.getContentIfNotHandled()?.let { }" 의
// 코드 사용을 줄이기 위해 사용하는 Observer
class EventObserver<T>(private val onEventUnhandledContent: (T?) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event ?: return

        if (event.content == null && !event.hasBeenHandled) {
            event.getContentIfNotHandled()
            onEventUnhandledContent(null)
            return
        }

        event.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}