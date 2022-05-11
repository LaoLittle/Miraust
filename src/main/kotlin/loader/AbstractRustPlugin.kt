package org.laolittle.loader

import jdk.tools.jlink.plugin.Plugin

abstract class AbstractRustPlugin(description: RustPluginDescription) : Plugin, NativePlugin {
    final override fun loadNative() {
        RustPluginManager.loadManagerLib()
    }

    init {
        loadNative()
    }
}