package raidline.pt.repo

import raidline.pt.model.Customer

sealed interface CustomerRepository {

    suspend fun getAllCustomers() : List<Customer>
    suspend fun getCustomerFromId(id : Int) : Customer?
    fun addCustomer(customer: Customer)
    suspend fun deleteCustomer(customerId: Int) : Boolean
}
