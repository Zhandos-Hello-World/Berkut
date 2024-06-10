package kz.cicada.berkut.lib.core.ui.compose.system

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager

class ExternalDeviceAppService(private val context: Context): DeviceAppService {

    override fun getBatteryPercentage(): Int {
        val iFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus = context.registerReceiver(null, iFilter)

        val level = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = batteryStatus?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1

        return (level / scale.toFloat() * 100).toInt()
    }
}