package com.curzy.specmd.domain.model

data class DeviceSpec(
    val deviceIdentity: SpecSection = SpecSection("Device Identity", "📱", emptyList()),
    val software: SpecSection = SpecSection("Software", "🔧", emptyList()),
    val hardware: SpecSection = SpecSection("Hardware", "⚙️", emptyList()),
    val display: SpecSection = SpecSection("Display", "📊", emptyList()),
    val battery: SpecSection = SpecSection("Battery", "🔋", emptyList()),
)

data class SpecSection(
    val title: String,
    val icon: String,
    val items: List<SpecItem>
)

data class SpecItem(
    val label: String,
    val value: String,
    val isAvailable: Boolean = true
)
