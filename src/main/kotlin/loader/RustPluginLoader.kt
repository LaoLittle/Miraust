package org.laolittle.loader

import net.mamoe.mirai.console.plugin.loader.PluginLoader
import java.nio.file.Path
import kotlin.io.path.absolutePathString

object RustPluginLoader : PluginLoader<RustPlugin, RustPluginDescription> {
    override fun disable(plugin: RustPlugin) {
        TODO("Not yet implemented")
    }

    override fun enable(plugin: RustPlugin) {
        TODO("Not yet implemented")
    }

    override fun getPluginDescription(plugin: RustPlugin): RustPluginDescription {

        TODO()
    }

    override fun listPlugins(): List<RustPlugin> {
        TODO("Not yet implemented")
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
}