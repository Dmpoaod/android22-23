package com.example.functions

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {

    fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onCreate(savedInstanceState)
        }
        setContentView(R.layout.activity_payment)

        btn_submit.setOnClickListener {
            val cardNumber = et_card_number.text.toString()
            val expiryDate = et_expiry_date.text.toString()
            val cvc = et_cvc.text.toString()

            if (cardNumber.isEmpty() || expiryDate.isEmpty() || cvc.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val payment = Payment(cardNumber, expiryDate, cvc)

            sendPaymentToServer(payment)
        }
    }

    class Bundle {

    }

    private fun sendPaymentToServer(payment: Payment) {
        val response = if (payment.isValid()) "Success" else "Failure"
        Toast.makeText(this, "Payment $response", Toast.LENGTH_SHORT).show()
    }
}

data class Payment(val cardNumber: String, val expiryDate: String, val cvc: String) {
    fun isValid(): Boolean {
        return true
    }
}
