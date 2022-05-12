package org.laolittle.loader

import net.mamoe.mirai.console.plugin.Plugin

interface NativePlugin : Plugin {
    fun loadNative()
}