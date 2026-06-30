package com.curzy.specmd.domain.repository

import com.curzy.specmd.domain.model.DeviceSpec

interface SpecRepository {
    fun getDeviceSpecs(): DeviceSpec
    fun generateMarkdown(spec: DeviceSpec): String
}
