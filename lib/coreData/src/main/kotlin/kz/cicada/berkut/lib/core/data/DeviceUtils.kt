package kz.cicada.berkut.lib.core.data

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION
import android.os.Build.VERSION.SDK_INT
import android.provider.Settings
import android.text.TextUtils
import android.util.DisplayMetrics
import java.util.UUID
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

class DefaultDeviceUtils(
    val context: Context,
) : DeviceUtils {

    override fun getApplicationName(): String {
        val applicationInfo = context.applicationInfo
        val stringId = applicationInfo.labelRes
        if (stringId == 0) return applicationInfo.nonLocalizedLabel.toString()

        return context.getString(stringId)
    }

    override fun getAppVersionCode(): Int {
        return try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            info.versionCode
        } catch (var3: PackageManager.NameNotFoundException) {
            0
        }
    }

    override fun getPackageName(): String {
        return context.packageName
    }

    override fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        if (model.startsWith(manufacturer)) return model

        return String.format("%s %s", manufacturer, model)
    }

    override fun getAppVersionName(): String {
        return try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            info.versionName
        } catch (var3: PackageManager.NameNotFoundException) {
            "n/a"
        }
    }

    @SuppressLint("HardwareIds")
    override fun getDeviceId(): String {
        val secureId = Settings.Secure.getString(context.contentResolver, "android_id")
        if (TextUtils.isEmpty(secureId)) return UUID.randomUUID().toString()
        return UUID.nameUUIDFromBytes(secureId.toByteArray()).toString()
    }

    override fun getAndroidVersion(): String {
        val release = VERSION.RELEASE
        val sdkVersion = SDK_INT
        return "$release ($sdkVersion)"
    }

    override fun getLinuxVersion(): String {
        return String.format("%s", System.getProperty("os.version"))
    }

    override fun getRuntimeVersion(): String {
        return String.format("%s", System.getProperty("java.vm.version"))
    }

    override fun getScreenInches(): Double {
        val dm: DisplayMetrics = context.resources.displayMetrics
        val x = (dm.widthPixels / dm.xdpi).toDouble().pow(2.0)
        val y = (dm.heightPixels / dm.ydpi).toDouble().pow(2.0)
        var screenInches = sqrt(x + y)
        screenInches = (screenInches * 10).roundToInt().toDouble() / 10
        return screenInches
    }

    override fun getDensityDpi(): Float {
        return context.resources.displayMetrics.density * 160f
    }

    override fun getUserAgent(): String {
        return String.format(
            "%s/%s Dalvik/%s (Linux %s; Android %s; %s; Build/%s; %s)",
            getPackageName(),
            getAppVersionName(),
            getRuntimeVersion(),
            getLinuxVersion(),
            getAndroidVersion(),
            getDeviceName(),
            getAppVersionCode(),
            getDeviceId(),
        )
    }

    override fun getDeviceDescription(): String {
        val manufacturer = Build.MANUFACTURER.orEmpty()
        val model = Build.MODEL.orEmpty()
        val description = if (model.startsWith(manufacturer)) model else "$manufacturer $model"

        return "$description | Android ${VERSION.RELEASE} | v${getAppVersionName()}"
    }

    override fun getAppVersionNameWithoutSuffix(): String =
        getAppVersionName().replace(VERSION_NAME_SUFFIX, EMPTY_STRING)

    private companion object {
        val VERSION_NAME_SUFFIX = Regex("-.+")
        const val EMPTY_STRING = ""
    }
}

interface DeviceUtils {

    fun getAndroidVersion(): String

    fun getLinuxVersion(): String

    fun getRuntimeVersion(): String

    fun getDeviceId(): String

    fun getAppVersionName(): String

    fun getDeviceName(): String

    fun getPackageName(): String

    fun getAppVersionCode(): Int

    fun getApplicationName(): String

    fun getScreenInches(): Double

    fun getDensityDpi(): Float

    fun getUserAgent(): String

    fun getDeviceDescription(): String

    fun getAppVersionNameWithoutSuffix(): String
}