package org.laolittle

import net.mamoe.mirai.console.command.CompositeCommand
import org.laolittle.loader.RustPluginLoader
import org.laolittle.loader.RustPluginManager

object ManagerCommand : CompositeCommand(
    Miraust, "miraust",
    description = "",
) {
    @SubCommand("list")
    fun list() {
        println("----------")
        RustPluginManager.plugins.forEach {
            println(it.description.name)
        }
    }

    @SubCommand("unload")
    fun unload(name: String) {
        RustPluginManager.plugins
            .firstOrNull { it.description.name == name }?.let { p ->
            RustPluginLoader.unload(p)
        }
    }
}