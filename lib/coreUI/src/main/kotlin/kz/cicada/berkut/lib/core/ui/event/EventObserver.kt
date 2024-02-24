package kz.cicada.berkut.lib.core.ui.event

import androidx.lifecycle.Observer

/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
 *
 * @author Jose Alcerreca, spizjeno by Aibek
 * @see <a href="https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150">
 *     Article
 *     </a>
 * @see <a href="https://gist.github.com/JoseAlcerreca/e0bba240d9b3cffa258777f12e5c0ae9">
 *     Source code
 *     </a>
 *
 *
 */
class EventObserver<T>(
    private val onEventUnhandledContent: (T) -> Unit,
) : Observer<Event<T>> {

    override fun onChanged(event: Event<T>) {
        event.get()?.let(onEventUnhandledContent)
    }
}

/*
Usage example
BEFORE
openTaskEvent.observe(this@TasksActivity, Observer<Event<String>> { event ->
    event.getContentIfNotHandled()?.let {
        openTaskDetails(it)
    }
})

AFTER
openTaskEvent.observe(this@TasksActivity, EventObserver(::openTaskDetails))
 */