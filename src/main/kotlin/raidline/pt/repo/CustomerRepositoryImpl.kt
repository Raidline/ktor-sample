package raidline.pt.repo

import kotlinx.coroutines.*
import raidline.pt.model.Customer

class CustomerRepositoryImpl : CustomerRepository {

    private val customers = generateSequence(1) { i -> i + 1 }.map { generateCustomer(it) }.take(10).toMutableList()

    private fun generateCustomer(id : Int) = Customer(id, "name$id", "lastname$id")

    override suspend fun getAllCustomers(): List<Customer> {
        return coroutineScope {
            delay(2000L)
            customers
        }
    }

    override suspend fun getCustomerFromId(id: Int): Customer? {
        return coroutineScope {
            customers.find { it.id == id }
        }
    }

    override fun addCustomer(customer: Customer) {
        customers.add(customer)
    }

    override suspend fun deleteCustomer(customerId: Int): Boolean {
        return coroutineScope {
            customers.removeIf { it.id == customerId }
        }
    }
}
