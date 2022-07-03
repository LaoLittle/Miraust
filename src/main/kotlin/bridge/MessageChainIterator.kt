@file: Suppress("Unused")

package org.laolittle.bridge

import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.SingleMessage

class MessageChainIterator(private val iter: Iterator<SingleMessage>): Iterator<SingleMessage> {
    override fun hasNext(): Boolean = iter.hasNext()

    override fun next(): SingleMessage = iter.next()

    companion object {
        @JvmStatic
        fun getIterator(chain: MessageChain): MessageChainIterator {
            val iter = chain.iterator()
            return MessageChainIterator(iter)
        }
    }
}