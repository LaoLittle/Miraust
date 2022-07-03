package org.laolittle.loader

import net.mamoe.mirai.console.plugin.PluginManager
import net.mamoe.mirai.console.plugin.loader.PluginLoader
import net.mamoe.mirai.console.plugin.name
import java.nio.file.Path
import kotlin.io.path.absolutePathString

object RustPluginLoader : PluginLoader<RustPlugin, RustPluginDescription> {
    override fun load(plugin: RustPlugin) {
        // nothing to do
        if (plugin.isEnabled) throw IllegalStateException("Plugin is already loaded")
    }

    override fun enable(plugin: RustPlugin) {
        if (plugin.isEnabled) throw IllegalStateException("Plugin ${plugin.description.name} is already enabled")
        enablePlugin(plugin.pluginPointer)
        plugin.enabled = true
    }

    override fun disable(plugin: RustPlugin) {
        if (plugin.isEnabled) {
            disablePlugin(plugin.pluginPointer)
            plugin.enabled = false
        }
    }

    fun unload(plugin: RustPlugin) {
        if (plugin.isDropped) throw IllegalStateException("Plugin ${plugin.name} already loaded")
        disable(plugin)
        unloadPlugin(plugin.pluginPointer)

        RustPluginManager.plugins.remove(plugin)

        plugin.dropped = true
    }

    override fun getPluginDescription(plugin: RustPlugin): RustPluginDescription {
        return plugin.description
    }

    override fun listPlugins(): List<RustPlugin> {
        return RustPluginManager.plugins
    }


    fun loadLibrary(libName: String): RustPlugin {
        return load(RustPluginManager.pluginsPath.resolve(System.mapLibraryName(libName)))
    }

    fun load(path: Path): RustPlugin {
        val pluginPointer = loadPlugin(path.absolutePathString())

        println("??")
        val (id, name, author, version) = getPluginDescription(pluginPointer)

        val loaded = RustPlugin(path, pluginPointer) {
            this.id = id
            this.name = name
            this.author = author
            this.setVersion(version)
        }

        RustPluginManager.plugins.add(loaded)
        PluginManager.loadPlugin(loaded) // 走走形式
        return loaded
    }

    private external fun loadPlugin(path: String): RawPointer

    private external fun enablePlugin(pluginPointer: RawPointer)

    private external fun disablePlugin(pluginPointer: RawPointer)

    /** Will close the library of this plugin */
    private external fun unloadPlugin(pluginPointer: RawPointer)

    private external fun getPluginDescription(pluginPointer: RawPointer): Array<String>

    init {
        RustPluginManager.loadManagerLib()
    }
}