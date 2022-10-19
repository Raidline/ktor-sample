package raidline.pt.routes

import io.ktor.server.application.*
import io.ktor.server.routing.*
import raidline.pt.handler.CustomerRouterHandler


fun Route.customerRouting(routerHandler: CustomerRouterHandler) {
    route("/customer") {
        get {
            routerHandler.handleGetAll(call)
        }
        get("{id?}") {
            routerHandler.handleGetSingleCustomer(call)
        }

        post {
            routerHandler.handleAddCustomer(call)
        }

        delete {
            routerHandler.handleDeleteCustomer(call)
        }
    }
}
