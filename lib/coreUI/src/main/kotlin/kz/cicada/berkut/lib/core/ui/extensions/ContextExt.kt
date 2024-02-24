package kz.cicada.berkut.lib.core.ui.extensions

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kz.cicada.berkut.core.presentation.R
import kz.cicada.berkut.lib.core.ui.event.OpenFileEvent
import java.io.File
import java.io.FileOutputStream
import java.util.Locale

private const val CONTENT_CLIPBOARD = "content"

/**
 * Возвращает false если файл не найден,
 * Возврашает true если файл найден, но его не удалось открыть тк нет подходящего приложения
 */
fun Context.openFile(event: OpenFileEvent): Boolean {
    val myExternalFile = File(
        getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
        event.filename,
    )

    if (event.bytes == null && !myExternalFile.exists()) {
        return false
    }

    try {
        event.bytes?.let {
            FileOutputStream(myExternalFile).apply {
                write(it)
                close()
            }
        }

        val fileUri = FileProvider.getUriForFile(
            this,
            "$packageName.appfileprovider",
            myExternalFile,
        )

        val intent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = fileUri
            this.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        startActivity(intent)
    } catch (ex: ActivityNotFoundException) {
        Toast
            .makeText(
                this,
                R.string.error_app_for_intent_not_found,
                Toast.LENGTH_SHORT,
            )
            .show()
    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
    return true
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.getColorCompat(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

fun Context.vibrate() {
    val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    // Vibrate for 300 milliseconds
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        v.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        // deprecated in API 26
        v.vibrate(300)
    }
}

fun Context.vibrateTick() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        v.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK))
    }
}

fun Context.getDrawableCompat(@DrawableRes res: Int) = ContextCompat.getDrawable(this, res)

fun Context.openPlayStoreApp(pkgName: String) {
    startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://play.google.com/store/apps/details?id=$pkgName"),
        ),
    )
}

fun Context.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.getResources(locale: Locale): Resources {
    val configuration = Configuration(resources.configuration)
    configuration.setLocale(locale)
    return createConfigurationContext(configuration).resources
}

fun Context.copyToClipboard(text: String) {
    (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
        .setPrimaryClip(ClipData.newPlainText(CONTENT_CLIPBOARD, text))
    Toast.makeText(
        this,
        this.getString(R.string.copied),
        Toast.LENGTH_SHORT,
    ).show()
}