package org.laolittle.loader

import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.plugin.PluginManager
import org.laolittle.librarySuffix
import java.io.File
import java.nio.file.Path
import kotlin.io.path.absolute
import kotlin.io.path.absolutePathString
import kotlin.io.path.createDirectories

object RustPluginManager {
    private var loaded = false
        @Synchronized set

    val rootPath: Path = MiraiConsole.rootPath.resolve("miraust").also { it.createDirectories() }

    internal val plugins: ArrayList<RustPlugin> = arrayListOf()

    val coreLibPath: Path = rootPath.resolve("core")
    val pluginsConfigPath: Path = rootPath.resolve("config")
    val pluginsConfigFolder: File = pluginsConfigPath.toFile()
    val pluginsDataPath: Path = rootPath.resolve("data")
    val pluginsDataFolder: File = pluginsDataPath.toFile()
    val pluginsPath: Path = rootPath
    val pluginsFolder: File = rootPath.toFile()

    fun getRootPath() = rootPath.absolutePathString()

    fun loadPlugins() {
        (pluginsFolder.listFiles() ?: return)
            .filter {
                println(it.absolutePath)
                it.extension == librarySuffix
            }
            .forEach { pluginFile ->
                RustPluginLoader.load(pluginFile.toPath().absolute())
            }
    }

    fun enablePlugins() {
        plugins.forEach(PluginManager::enablePlugin)
    }

    internal fun loadManagerLib() {
        if (!loaded) {
            val path = coreLibPath.also { it.createDirectories() }.resolve(System.mapLibraryName("miraust_core"))
            System.load(path.absolutePathString())
            loaded = true
        }
    }

    init {
        loadManagerLib()
    }
}