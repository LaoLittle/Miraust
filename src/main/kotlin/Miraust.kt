package org.laolittle

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.extension.PluginComponentStorage
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info
import org.laolittle.loader.RustPluginManager

object Miraust : KotlinPlugin(
    JvmPluginDescription(
        id = "org.laolittle.Miraust",
        name = "Miraust",
        version = "0.1.0",
    ) {
        author("LaoLittle")
    }
) {
    override fun PluginComponentStorage.onLoad() {
        // contributePluginLoader { RustPluginLoader }

        RustPluginManager.loadPlugins()
    }

    override fun onEnable() {
        ManagerCommand.register()
        logger.info { "Plugin loaded" }

        EventHandler.start()
        RustPluginManager.enablePlugins()
    }
}