package com.curzy.specmd.data.repository

import com.curzy.specmd.data.source.DeviceInfoSource
import com.curzy.specmd.data.source.SystemInfoSource
import com.curzy.specmd.domain.model.DeviceSpec
import com.curzy.specmd.domain.model.SpecSection
import com.curzy.specmd.domain.repository.SpecRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpecRepositoryImpl @Inject constructor(
    private val deviceInfoSource: DeviceInfoSource,
    private val systemInfoSource: SystemInfoSource
) : SpecRepository {

    override fun getDeviceSpecs(): DeviceSpec {
        return DeviceSpec(
            deviceIdentity = deviceInfoSource.getDeviceIdentity(),
            software = deviceInfoSource.getSoftware(),
            hardware = systemInfoSource.getHardware(),
            display = systemInfoSource.getDisplay(),
            battery = systemInfoSource.getBattery()
        )
    }

    override fun generateMarkdown(spec: DeviceSpec): String {
        val now = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        ) + " WIB"

        return buildString {
            appendLine("# 📱 Device Specifications")
            appendLine()
            appendLine("**Generated:** $now")
            appendLine()

            appendSection(this, spec.deviceIdentity)
            appendSection(this, spec.software)
            appendSection(this, spec.hardware)
            appendSection(this, spec.display)
            appendSection(this, spec.battery)

            appendLine("---")
            appendLine("*Exported via SpecMD*")
        }
    }

    private fun appendSection(sb: StringBuilder, section: SpecSection) {
        sb.appendLine("## ${section.icon} ${section.title}")
        sb.appendLine()
        sb.appendLine("| Property | Value |")
        sb.appendLine("|----------|-------|")

        section.items.forEach { item ->
            val value = if (item.isAvailable) item.value else "N/A"
            sb.appendLine("| ${item.label} | $value |")
        }
        sb.appendLine()
    }
}
