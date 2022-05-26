package org.laolittle

import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.job
import net.mamoe.mirai.event.Event
import net.mamoe.mirai.event.EventPriority
import net.mamoe.mirai.event.Listener
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.globalEventChannel
import kotlin.coroutines.CoroutineContext

private val handlerJob = SupervisorJob(Miraust.coroutineContext.job)

object EventHandler : CompletableJob by handlerJob, CoroutineScope {
    private var listener: Listener<*>? = null
    override val coroutineContext: CoroutineContext
        get() = handlerJob

    override fun start(): Boolean {
        listener = listenHighest<Event> {
            broadcastToRs(this)
        }

        return job.start()
    }

    override fun complete(): Boolean {
        return listener?.complete() ?: false && handlerJob.complete()
    }

    private inline fun <reified E : Event> listenHighest(noinline handler: suspend E.(E) -> Unit) =
        globalEventChannel().subscribeAlways(priority = EventPriority.HIGHEST, handler = handler)

    fun broadcastToRs(event: Event) {
        val type: Byte = when (event) {
            is GroupMessageEvent -> 1
            is FriendMessageEvent -> 2
            else -> 0
        }

        broadcast(event, type)
    }

    private external fun broadcast(event: Event, type: Byte)
}