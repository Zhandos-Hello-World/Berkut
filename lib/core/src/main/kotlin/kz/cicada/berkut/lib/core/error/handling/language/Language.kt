package kz.cicada.berkut.lib.core.error.handling.language

import androidx.annotation.StringRes
import kz.cicada.berkut.lib.core.R

enum class Language(@StringRes val resId: Int) {
    KK(R.string.kazakh),
    RU(R.string.russian),
    NOT_SELECTED(R.string.language_selection),
}
