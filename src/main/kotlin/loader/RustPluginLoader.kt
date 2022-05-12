package org.laolittle.loader

import net.mamoe.mirai.console.plugin.loader.PluginLoader
import java.nio.file.Path
import kotlin.io.path.absolutePathString

object RustPluginLoader : PluginLoader<RustPlugin, RustPluginDescription> {
    override fun enable(plugin: RustPlugin) {
        for ((id, p) in RustPluginManager.plugins.withIndex()) {
            println(p === plugin)
            if (p === plugin) {
                enablePlugin(id)
                break
            }
        }
    }

    override fun disable(plugin: RustPlugin) {
        for ((id, p) in RustPluginManager.plugins.withIndex()) {
            if (p === plugin) {
                disablePlugin(id)
                break
            }
        }
    }

    override fun getPluginDescription(plugin: RustPlugin): RustPluginDescription {
        return plugin.description
    }

    override fun listPlugins(): List<RustPlugin> {
        return RustPluginManager.plugins
    }

    override fun load(plugin: RustPlugin) {
        loadRustPlugin(plugin.absolutePath)
    }

    fun load(libName: String): RustPlugin {
        return load(RustPluginManager.pluginsPath.resolve(System.mapLibraryName(libName)))
    }

    fun load(path: Path): RustPlugin {
        loadRustPlugin(path.absolutePathString())
        val pluginId = RustPluginManager.plugins.size

        val desc = getPluginDescription(pluginId)
        val (id, name, author, version) = desc

        val loaded = RustPlugin(path) {
            this.id = id
            this.name = name
            this.author = author
            this.setVersion(version)
        }

        RustPluginManager.plugins.add(loaded)
        return loaded
    }

    private external fun loadRustPlugin(path: String)

    private external fun getPluginDescription(pluginId: Int): Array<String>

    private external fun enablePlugin(pluginId: Int)

    private external fun disablePlugin(pluginId: Int)
}