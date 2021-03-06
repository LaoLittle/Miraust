package org.laolittle.loader

import net.mamoe.mirai.console.plugin.description.PluginDependency
import net.mamoe.mirai.console.plugin.description.PluginDescription
import net.mamoe.mirai.console.util.SemVersion

data class RustPluginDescription(
    override val author: String,
    override val dependencies: Set<PluginDependency>,
    override val id: String,
    override val info: String,
    override val name: String,
    override val version: SemVersion
) : PluginDescription

class RustPluginDescriptionBuilder {
    var author: String = ""

    val dependencies: Set<PluginDependency> = setOf() // todo

    var id: String = ""
    var info: String = ""
    var name: String = id
    private var version: SemVersion = SemVersion("0.0.1")

    fun setVersion(version: String) {
        this.version = SemVersion(version)
    }

    fun build() = RustPluginDescription(author, dependencies, id, info, name, version)
}

fun buildRustPluginDescription(block: RustPluginDescriptionBuilder.() -> Unit): RustPluginDescription {
    return RustPluginDescriptionBuilder().apply(block).build()
}