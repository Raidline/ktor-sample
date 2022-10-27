package raidline.pt.logger

import io.ktor.server.application.*
import io.ktor.server.request.*

fun ApplicationCall.logger() = this.application.environment.log

fun logRequest(call : ApplicationCall) {
    println("The URI ${call.request.uri} was called with parameters ${call.parameters}, query string ${call.request.queryParameters} and headers " +
            "${call.request.headers}")
}
