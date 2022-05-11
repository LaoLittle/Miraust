package org.laolittle.loader

import net.mamoe.mirai.console.permission.Permission
import net.mamoe.mirai.console.permission.PermissionId
import net.mamoe.mirai.console.plugin.Plugin
import net.mamoe.mirai.console.plugin.description.PluginDependency
import net.mamoe.mirai.console.plugin.description.PluginDescription
import net.mamoe.mirai.console.plugin.loader.PluginLoader
import net.mamoe.mirai.console.util.SemVersion
import java.nio.file.Path

class RustPlugin (val path: Path) : Plugin {
    constructor(libName: String): this(RustPluginManager.pluginsPath.resolve(System.mapLibraryName(libName)))


    override val isEnabled: Boolean
        get() = TODO("Not yet implemented")
    override val loader: PluginLoader<*, *>
        get() = RustPluginLoader
    override val parentPermission: Permission
        get() = TODO("Not yet implemented")

    override fun permissionId(name: String): PermissionId {
        TODO("Not yet implemented")
    }
}

class RustPluginDescription(
    override val author: String,
    override val dependencies: Set<PluginDependency>,
    override val id: String,
    override val info: String,
    override val name: String,
    override val version: SemVersion
) : PluginDescription

class RustPluginDescriptionBuilder {
    var author: String = ""

    val dependencies: Set<PluginDependency>
    get() {
        external fun a ()
        TODO()
    }

    var id: String = ""
    var info: String = ""
    var name: String = id
    var version: SemVersion = SemVersion("0.0.0")

    fun setVersion(version: String) {
        this.version = SemVersion(version)
    }

    fun build() = RustPluginDescription(author, dependencies, id, info, name, version)

    external fun set()
}

fun buildRustPluginDescription(block: RustPluginDescriptionBuilder.() -> Unit): RustPluginDescription {
    return RustPluginDescriptionBuilder().apply(block).build()
}