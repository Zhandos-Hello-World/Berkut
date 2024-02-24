package kz.cicada.berkut.lib.core.ui.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Patterns
import androidx.core.text.HtmlCompat
import androidx.core.text.isDigitsOnly
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import timber.log.Timber
import java.util.Locale

fun String?.toCardNumberMasked(): String = this.orEmpty().replace('_', '*').toMaskedSpaceEvery4()

fun String.countSpaces(): Int = this.count { it == ' ' }

fun String.removeSpaces(): String = this.replace(" ", "")

fun String.toMaskedSpaceEvery4(): String {
    if (length <= 4) {
        return this
    }
    var masked = substring(0, 4)
    for (i in 4..length step 4) {
        masked += " ${substring(i, i + minOf(4, length - i))}"
    }
    return masked
}

fun String.toMaskedSpaceCenter(): String {
    if (length <= 1) {
        return this
    }
    val indexCenter: Int = length / 2
    return substring(0, indexCenter) + " " + substring(indexCenter, length)
}

fun String?.containedIn(vararg list: String): Boolean {
    return if (this == null) false else list.contains(this)
}

fun String.insert(index: Int, string: String): String {
    return this.substring(0, index) + string + this.substring(index, this.length)
}

fun String.toHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}

fun String.openInBrowser(context: Context) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(this))
    browserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(Intent.createChooser(browserIntent, null))
}

fun String.capitalizeText(): String {
    return lowercase().replaceFirstChar { it.titlecase(Locale.getDefault()) }
}

fun String.capitalizeFirst(): String {
    return replaceFirstChar { it.titlecase(Locale.getDefault()) }
}

fun String.isNotDigitsOnly(): Boolean {
    return this.isDigitsOnly().not()
}

fun String.isValidUrl(): Boolean {
    return Patterns.WEB_URL.matcher(this).matches()
}

fun String?.parseToLocalDateOrNull(): LocalDate? = try {
    LocalDate.parse(this)
} catch (e: Exception) {
    Timber.e(e)
    null
}

fun String?.parseToLocalDateWithPatternOrNull(pattern: String): LocalDate? = try {
    LocalDate.parse(this, DateTimeFormat.forPattern(pattern))
} catch (e: Exception) {
    Timber.e(e)
    null
}

fun String?.parseToLocalDateTimeOrNull(): LocalDateTime? = try {
    LocalDateTime.parse(this)
} catch (e: Exception) {
    Timber.e(e)
    null
}