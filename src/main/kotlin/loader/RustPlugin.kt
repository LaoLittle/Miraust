package org.laolittle.loader

import net.mamoe.mirai.console.permission.Permission
import net.mamoe.mirai.console.permission.PermissionId
import net.mamoe.mirai.console.plugin.Plugin
import net.mamoe.mirai.console.plugin.loader.PluginLoader
import java.nio.file.Path
import kotlin.io.path.absolutePathString

class RustPlugin(val path: Path, override val description: RustPluginDescription) : Plugin, AbstractRustPlugin() {
    constructor(path: Path, builderAction: RustPluginDescriptionBuilder.() -> Unit) : this(
        path,
        buildRustPluginDescription(builderAction)
    )

    val absolutePath = path.absolutePathString()

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
