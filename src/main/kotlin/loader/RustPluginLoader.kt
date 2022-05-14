package org.laolittle.loader

import net.mamoe.mirai.console.plugin.loader.PluginLoader
import java.nio.file.Path
import kotlin.io.path.absolutePathString

object RustPluginLoader : PluginLoader<RustPlugin, RustPluginDescription> {
    override fun enable(plugin: RustPlugin) {
        enablePlugin(plugin.pluginPointer)
        plugin.enabled = true
    }

    override fun disable(plugin: RustPlugin) {
        disablePlugin(plugin.pluginPointer)
        plugin.enabled = false
    }

    override fun getPluginDescription(plugin: RustPlugin): RustPluginDescription {
        return plugin.description
    }

    override fun listPlugins(): List<RustPlugin> {
        return RustPluginManager.plugins
    }

    override fun load(plugin: RustPlugin) {
        loadPlugin(plugin.absolutePath)
    }

    fun load(libName: String): RustPlugin {
        return load(RustPluginManager.pluginsPath.resolve(System.mapLibraryName(libName)))
    }

    private fun load(path: Path): RustPlugin {
        val pluginPointer = loadPlugin(path.absolutePathString())

        val (id, name, author, version) = getPluginDescription(pluginPointer)

        val loaded = RustPlugin(path, pluginPointer) {
            this.id = id
            this.name = name
            this.author = author
            this.setVersion(version)
        }

        RustPluginManager.plugins.add(loaded)
        return loaded
    }

    private external fun loadPlugin(path: String): RawPointer

    /** Will close the library of this plugin */
    private external fun unloadPlugin(pluginPointer: RawPointer)

    private external fun getPluginDescription(pluginPointer: RawPointer): Array<String>

    private external fun enablePlugin(pluginPointer: RawPointer)

    private external fun disablePlugin(pluginPointer: RawPointer)
}