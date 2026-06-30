package com.curzy.specmd.data.source

import android.app.ActivityManager
import android.content.Context
import android.graphics.Point
import android.os.Environment
import android.os.StatFs
import com.curzy.specmd.domain.model.SpecItem
import com.curzy.specmd.domain.model.SpecSection
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SystemInfoSource @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getHardware(): SpecSection {
        val cpuInfo = parseCpuInfo()
        val ramInfo = getRamInfo()
        val storageInfo = getStorageInfo()

        return SpecSection(
            title = "Hardware",
            icon = "⚙️",
            items = listOf(cpuInfo, ramInfo, storageInfo)
        )
    }

    private fun parseCpuInfo(): SpecItem {
        return try {
            val cpuInfoText = File("/proc/cpuinfo").readText()
            val processor = cpuInfoText.lines()
                .find { it.startsWith("model name") || it.startsWith("Processor") }
                ?.substringAfter(":")?.trim()
                ?: "N/A"

            val hardware = cpuInfoText.lines()
                .find { it.startsWith("Hardware") }
                ?.substringAfter(":")?.trim()

            val cpuText = if (hardware != null) "$processor ($hardware)" else processor
            SpecItem("Processor", cpuText)
        } catch (e: Exception) {
            SpecItem("Processor", "N/A", false)
        }
    }

    private fun getRamInfo(): SpecItem {
        return try {
            val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val memoryInfo = ActivityManager.MemoryInfo()
            activityManager.getMemoryInfo(memoryInfo)
            val totalGB = memoryInfo.totalMem / (1024.0 * 1024 * 1024)
            SpecItem("RAM", String.format("%.1f GB", totalGB))
        } catch (e: Exception) {
            SpecItem("RAM", "N/A", false)
        }
    }

    private fun getStorageInfo(): SpecItem {
        return try {
            val path = Environment.getDataDirectory()
            val statFs = StatFs(path.path)
            val totalGB = statFs.totalBytes / (1024.0 * 1024 * 1024)
            SpecItem("Internal Storage", String.format("%.0f GB", totalGB))
        } catch (e: Exception) {
            SpecItem("Internal Storage", "N/A", false)
        }
    }

    fun getDisplay(): SpecSection {
        return try {
            val metrics = context.resources.displayMetrics

            // Try to get display info safely
            var width = metrics.widthPixels
            var height = metrics.heightPixels
            var refreshRate = 60f

            try {
                val display: android.view.Display? = context.display
                if (display != null) {
                    val size = Point()
                    @Suppress("DEPRECATION")
                    display.getRealSize(size)
                    width = size.x
                    height = size.y
                    refreshRate = display.refreshRate
                }
            } catch (e: Exception) {
                // Fallback: use metrics directly
            }

            SpecSection(
                title = "Display",
                icon = "📊",
                items = listOf(
                    SpecItem("Resolution", "$width x $height pixels"),
                    SpecItem("Density", "$metrics.densityDpi dpi"),
                    SpecItem("Refresh Rate", "${refreshRate.toInt()} Hz")
                )
            )
        } catch (e: Exception) {
            SpecSection(
                title = "Display",
                icon = "📊",
                items = listOf(
                    SpecItem("Resolution", "N/A", false),
                    SpecItem("Density", "N/A", false),
                    SpecItem("Refresh Rate", "N/A", false)
                )
            )
        }
    }

    fun getBattery(): SpecSection {
        return try {
            val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as android.os.BatteryManager
            val capacity = batteryManager.getIntProperty(android.os.BatteryManager.BATTERY_PROPERTY_CAPACITY)
            val status = batteryManager.isCharging

            SpecSection(
                title = "Battery",
                icon = "🔋",
                items = listOf(
                    SpecItem("Capacity", "$capacity%"),
                    SpecItem("Status", if (status) "Charging" else "Discharging")
                )
            )
        } catch (e: Exception) {
            SpecSection(
                title = "Battery",
                icon = "🔋",
                items = listOf(SpecItem("Capacity", "N/A", false))
            )
        }
    }
}
