package org.laolittle

import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.job
import net.mamoe.mirai.event.Event
import net.mamoe.mirai.event.EventPriority
import net.mamoe.mirai.event.Listener
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.message.data.content
import kotlin.coroutines.CoroutineContext

private val handlerJob = SupervisorJob(Miraust.coroutineContext.job)
object EventHandler: CompletableJob by handlerJob, CoroutineScope {
    private val listeners = mutableListOf<Listener<*>>()
    override val coroutineContext: CoroutineContext
        get() = handlerJob

    override fun start(): Boolean {
        listeners.add(listenHighest<Event> {
            when (this) {
                is FriendMessageEvent -> {
                    broadcast(message.content)
                }
            }
        })

        return job.start()
    }

    override fun complete(): Boolean {
        listeners.forEach(Listener<*>::complete)
        return (job as CompletableJob).complete()
    }

    private inline fun <reified E: Event> listenHighest(noinline handler: suspend E.(E) -> Unit) = globalEventChannel().subscribeAlways(priority = EventPriority.HIGHEST, handler = handler)

    private external fun broadcast(msg: String)
}