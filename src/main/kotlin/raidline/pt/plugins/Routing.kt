package raidline.pt.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import raidline.pt.handler.CustomerRouterHandler
import raidline.pt.repo.CustomerRepositoryImpl
import raidline.pt.routes.customerRouting

fun Application.configureRouting() {

    routing {
        customerRouting(routerHandler = CustomerRouterHandler(CustomerRepositoryImpl()))
    }
}
