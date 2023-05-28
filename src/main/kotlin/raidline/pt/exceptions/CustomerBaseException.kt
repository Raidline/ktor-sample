package raidline.pt.exceptions

import io.ktor.http.*

sealed class CustomerBaseException(override val message: String, val code: HttpStatusCode) : Exception(message)


class CustomerInternalException(message: String, code: HttpStatusCode = HttpStatusCode.InternalServerError) :
    CustomerBaseException(message, code)

class CustomerBadRequestException(message: String, code: HttpStatusCode = HttpStatusCode.BadRequest) :
    CustomerBaseException(message, code)

class CustomerNotFoundException(message: String, code: HttpStatusCode = HttpStatusCode.NotFound) :
    CustomerBaseException(message, code)
