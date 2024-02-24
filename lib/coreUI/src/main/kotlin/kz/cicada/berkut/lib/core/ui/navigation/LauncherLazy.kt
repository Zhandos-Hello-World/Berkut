package kz.cicada.berkut.lib.core.ui.navigation

import android.os.Bundle
import kz.cicada.berkut.lib.core.ui.extensions.getLauncher

/**
 * An implementation of [Lazy] used by [androidx.fragment.app.Fragment.launcherLazy].
 *
 * [argumentProducer] is a lambda that will be called during initialization to provide
 * arguments to construct an [Args] instance via reflection.
 */
class LauncherLazy<Args : Any>(
    private val argumentProducer: () -> Bundle,
) : Lazy<Args> {

    private var cached: Args? = null

    override val value: Args
        get() {
            var args = cached
            if (args == null) {
                val arguments = argumentProducer()
                args = arguments.getLauncher()
                cached = args
            }
            return args
        }

    override fun isInitialized() = cached != null
}
