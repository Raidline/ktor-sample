package raidline.pt.handler

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import raidline.pt.logger.logRequest
import raidline.pt.model.Customer
import raidline.pt.repo.CustomerRepositoryImpl

class CustomerRouterHandler(private val customerRepository: CustomerRepositoryImpl) {

    private companion object {
        const val CUSTOMER_ID_PARAM = "id"
    }

    suspend fun handleGetAll(call: ApplicationCall) {
        logRequest(call)

        val response = customerRepository.getAllCustomers()

        if(response.isEmpty()) {
            call.respond(status = HttpStatusCode.NotFound, "There are no customers at this point in time")
            return
        }

        call.respond(HttpStatusCode.OK, response)
    }

    suspend fun handleGetSingleCustomer(call: ApplicationCall) {
        logRequest(call)

        val customerId = call.parameters.getCustomerId()

        val response = customerRepository.getCustomerFromId(customerId)

        if (response == null) {
            call.respond(status = HttpStatusCode.NotFound, "There are no customers with id $customerId")
            return
        }

        call.respond(HttpStatusCode.OK, response)
    }

    suspend fun handleAddCustomer(call: ApplicationCall) {
        logRequest(call)

        val customer = call.receive<Customer>()

        customerRepository.addCustomer(customer)

        call.respond(status = HttpStatusCode.Created, "Customer created")
    }

    suspend fun handleDeleteCustomer(call: ApplicationCall) {
        logRequest(call)

        val customerId = call.parameters.getCustomerId()

        val deleteCustomer = customerRepository.deleteCustomer(customerId)

        call.respond(status = HttpStatusCode.OK, "Customer was deleted? -> $deleteCustomer")
    }

    private fun Parameters.getCustomerId(): Int =
        this[CUSTOMER_ID_PARAM]?.toIntOrNull() ?: throw IllegalArgumentException("There is no customer id param found")
}
