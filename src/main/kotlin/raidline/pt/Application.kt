package raidline.pt

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import raidline.pt.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost") {
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}
