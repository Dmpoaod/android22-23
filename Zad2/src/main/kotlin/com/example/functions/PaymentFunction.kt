package com.example.functions

data class PaymentData(val cardNumber: String,
                       val expirationDate: String,
                       val cvv: String,
                       val amount: Double)

fun mockPayment (paymentData : PaymentData): Boolean {
    return paymentData.amount > 0 && paymentData.cardNumber.length == 16 && paymentData.expirationDate.isNotEmpty() && paymentData.cvv.length == 3
}