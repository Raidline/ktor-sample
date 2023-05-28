package raidline.pt

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.statuspages.*
import raidline.pt.errorhandling.setup
import raidline.pt.plugins.configureRouting
import raidline.pt.plugins.configureSerialization

fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost", module = Application::customerModule).start(wait = true)
}

fun Application.module() {
    install(StatusPages) {
        setup()
    }
}

fun Application.customerModule() {
    configureSerialization()
    configureRouting()
    module()
}
