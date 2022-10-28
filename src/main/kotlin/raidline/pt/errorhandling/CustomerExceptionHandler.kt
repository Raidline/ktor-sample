package raidline.pt.errorhandling

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import raidline.pt.errorhandling.response.CustomerErrorResponse
import raidline.pt.exceptions.CustomerBadRequestException
import raidline.pt.exceptions.CustomerBaseException
import raidline.pt.exceptions.CustomerInternalException
import raidline.pt.exceptions.CustomerNotFoundException
import raidline.pt.logger.logger


fun StatusPagesConfig.setup() {
    exception<Exception> { call, exception -> exception.handleCustomerException(call).also { call.logger().info("CAME FROM STATUS PAGE") } }
}


suspend fun Exception.handleCustomerException(call : ApplicationCall) =
    when(this) {
        is CustomerInternalException -> {
            call.respond(this.code, this.convertExceptionToError()).also {
                call.logger().error("There was an internal error in the application")
            }
        }
        is CustomerNotFoundException -> {
            call.respond(this.code, this.convertExceptionToError()).also {
                call.logger().warn("The wanted resource could not be found")
            }
        }
        is CustomerBadRequestException -> {
            call.respond(this.code, this.convertExceptionToError()).also {
                call.logger().warn("User sent a bad request")
            }
        }
        else -> call.respond(HttpStatusCode.InternalServerError, "Unknown error")
    }


private fun <T : CustomerBaseException> T.convertExceptionToError() = CustomerErrorResponse(this.message, this.code.value)
