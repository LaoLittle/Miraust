@file: JvmName("MessageBridge")
@file: Suppress("Unused")

package org.laolittle.bridge

import net.mamoe.mirai.message.data.*

fun SingleMessage.type(): Byte {
    return when (this) {
        is PlainText -> 1
        is At -> 2
        is AtAll -> 3
        is Image -> 4
        is RichMessage -> 5
        is Face -> 6
        is ForwardMessage -> 7
        is Audio -> 8
        is MarketFace -> 9
        is MusicShare -> 10
        else -> 0
    }
}