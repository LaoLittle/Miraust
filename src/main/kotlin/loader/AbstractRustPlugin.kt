package org.laolittle.loader

import net.mamoe.mirai.console.plugin.Plugin

abstract class AbstractRustPlugin : Plugin, NativePlugin {
    abstract val description: RustPluginDescription

    final override fun loadNative() {
        RustPluginManager.loadManagerLib()
    }

    init {
        loadNative()
    }
}