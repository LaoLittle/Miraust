package org.laolittle.loader

import net.mamoe.mirai.console.MiraiConsole
import org.laolittle.librarySuffix
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Path
import kotlin.io.path.absolutePathString

object RustPluginManager {
    private var loaded = false
        @Synchronized set

    val rootPath: Path = MiraiConsole.rootPath.resolve("miraust").also { it.toFile().mkdirs() }

    internal val plugins: ArrayList<RustPlugin> = arrayListOf()

    val pluginsConfigPath: Path = rootPath.resolve("config")
    val pluginsConfigFolder: File = pluginsConfigPath.toFile()
    val pluginsDataPath: Path = rootPath.resolve("data")
    val pluginsDataFolder: File = pluginsDataPath.toFile()
    val pluginsPath: Path = rootPath
    val pluginsFolder: File = rootPath.toFile()

    fun getRootPath() = rootPath.absolutePathString()

    fun loadPlugins() {
        (pluginsFolder.listFiles() ?: throw FileNotFoundException("空文件夹"))
            .asSequence()
            .filter { it.extension == librarySuffix }
            .forEach { pluginFile ->
                // todo!!
            }
    }

    fun loadManagerLib() {
        if (!loaded) {
            val path = rootPath.resolve(System.mapLibraryName("miraust"))
            System.load(path.absolutePathString())
            loaded = true
        }
    }

    init {
        loadManagerLib()
    }
}