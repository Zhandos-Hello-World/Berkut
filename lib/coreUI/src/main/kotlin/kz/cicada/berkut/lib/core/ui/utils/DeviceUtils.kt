package kz.cicada.berkut.lib.core.ui.utils

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import kz.cicada.berkut.core.presentation.R
import kz.cicada.berkut.lib.core.ui.extensions.openInBrowser

object DeviceUtils {

    fun link(context: Context?, url: String) {
        try {
            context?.also { url.openInBrowser(context) }
        } catch (ex: ActivityNotFoundException) {
            Toast
                .makeText(context, R.string.error_app_for_intent_not_found, Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun call(context: Context?, phone: String) {
        try {
            context?.startActivity(
                Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                },
            )
        } catch (ex: ActivityNotFoundException) {
            Toast
                .makeText(context, R.string.error_app_for_intent_not_found, Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun share(context: Context?, message: String, subject: String?) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
            subject?.let { putExtra(Intent.EXTRA_SUBJECT, it) }
        }
        context?.startActivity(Intent.createChooser(intent, message))
    }

    fun goToAppSettings(context: Context?) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", context?.packageName, null)
        intent.data = uri
        context?.startActivity(intent)
    }

    fun copyToClipboard(context: Context, text: String) {
        (context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
            .setPrimaryClip(ClipData.newPlainText("content", text))

        Toast.makeText(context, context.getString(R.string.copied), Toast.LENGTH_SHORT)
            .show()
    }
}