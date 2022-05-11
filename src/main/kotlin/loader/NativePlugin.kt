package org.laolittle.loader

import jdk.tools.jlink.plugin.Plugin

interface NativePlugin : Plugin {
    fun loadNative()
}