package org.laolittle.loader

import net.mamoe.mirai.console.permission.Permission
import net.mamoe.mirai.console.permission.PermissionId
import net.mamoe.mirai.console.plugin.loader.PluginLoader
import java.nio.file.Path
import kotlin.io.path.absolutePathString

class RustPlugin(
    path: Path,
    override val pluginPointer: RawPointer,
    override val description: RustPluginDescription,
) : NativePlugin, AbstractRustPlugin() {
    constructor(path: Path, pluginPointer: RawPointer, builderAction: RustPluginDescriptionBuilder.() -> Unit) : this(
        path,
        pluginPointer,
        buildRustPluginDescription(builderAction)
    )

    val absolutePath = path.absolutePathString()

    internal var enabled = false

    override val isEnabled: Boolean
        get() = enabled
    override val loader: PluginLoader<*, *>
        get() = RustPluginLoader
    override val parentPermission: Permission
        get() = TODO("Not yet implemented")

    override fun permissionId(name: String): PermissionId {
        TODO("Not yet implemented")
    }
}