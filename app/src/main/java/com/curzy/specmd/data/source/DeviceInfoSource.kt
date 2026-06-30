package com.curzy.specmd.data.source

import android.os.Build
import com.curzy.specmd.domain.model.SpecItem
import com.curzy.specmd.domain.model.SpecSection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceInfoSource @Inject constructor() {

    fun getDeviceIdentity(): SpecSection {
        return SpecSection(
            title = "Device Identity",
            icon = "📱",
            items = listOf(
                SpecItem("Model", Build.MODEL),
                SpecItem("Manufacturer", Build.MANUFACTURER),
                SpecItem("Brand", Build.BRAND),
                SpecItem("Device", Build.DEVICE),
                SpecItem("Product", Build.PRODUCT)
            )
        )
    }

    fun getSoftware(): SpecSection {
        val securityPatch = try {
            Build.VERSION.SECURITY_PATCH
        } catch (e: Exception) {
            "N/A"
        }

        return SpecSection(
            title = "Software",
            icon = "🔧",
            items = listOf(
                SpecItem("Android Version", "Android ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})"),
                SpecItem("Security Patch", securityPatch),
                SpecItem("Build Number", Build.ID),
                SpecItem("Build Fingerprint", Build.FINGERPRINT.take(40) + "...")
            )
        )
    }
}
