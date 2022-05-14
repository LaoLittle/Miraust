package org.laolittle.loader

import net.mamoe.mirai.console.plugin.Plugin

internal typealias RawPointer = Long
interface NativePlugin : Plugin {
    fun loadNative()
}