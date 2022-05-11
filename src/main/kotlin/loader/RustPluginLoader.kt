package org.laolittle.loader

import net.mamoe.mirai.console.plugin.loader.PluginLoader
import kotlin.io.path.absolutePathString

object RustPluginLoader : PluginLoader<RustPlugin, RustPluginDescription> {
    override fun disable(plugin: RustPlugin) {
        TODO("Not yet implemented")
    }

    override fun enable(plugin: RustPlugin) {
        TODO("Not yet implemented")
    }

    override fun getPluginDescription(plugin: RustPlugin): RustPluginDescription {
        getPluginDescription(plugin.absolutePath)
        TODO()
    }

    override fun listPlugins(): List<RustPlugin> {
        TODO("Not yet implemented")
    }

    override fun load(plugin: RustPlugin) {
        loadRustPlugin(plugin.path.absolutePathString())
    }

    private external fun loadRustPlugin(path: String)

    private external fun getPluginDescription(path: String)
}