package com.example.pumazad4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.parcelize.Parcelize

class MainActivity : AppCompatActivity() {

    private var canAddOperation = false
    private var canAddDec = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun operationAction(view: View) {

        if (view is Button && canAddOperation) {
            findViewById<TextView>(R.id.calculations).append(view.text)
            canAddOperation = false
            canAddDec = true
        }

    }

    fun numberAction(view: View) {
        if (view is Button) {
            if (view.text == ".") {
                if (canAddDec) {
                    findViewById<TextView>(R.id.calculations).append(view.text)
                }
                canAddDec = false
            }
            else
                findViewById<TextView>(R.id.calculations).append(view.text)

            canAddOperation = true

        }
    }

    fun allClearAction(view: View) {

        findViewById<TextView>(R.id.calculations).text = ""
        findViewById<TextView>(R.id.results).text = ""
        canAddOperation = false
        canAddDec = true
    }

    fun clearToLeftAction(view: View) {
        val TV = findViewById<TextView>(R.id.calculations)

        val length = TV.length()
        if (length > 0)
            TV.text = TV.text.subSequence(0, length-1)
    }

    fun equalsAction(view: View) {
        val resTV = findViewById<TextView>(R.id.results)
        resTV.text = calculateRes()

    }

    private fun calculateRes(): String {

        val digitsOps = digitsOps()
        if (digitsOps.isEmpty()) return ""

        val timesDivs = timesDivsCalc(digitsOps)
        if (timesDivs.isEmpty()) return ""

        val result = addSubCalc(timesDivs)
        return result.toString()

    }

    private fun addSubCalc(passedList: MutableList<Any>): Float {
        var res = passedList[0] as Float

        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex) {
                val operator = passedList[i]
                val next = passedList[i+1] as Float

                if (operator == '＋')
                    res += next

                if (operator == '－')
                    res -= next
            }
        }

      return res
    }

    private fun timesDivsCalc(passedList: MutableList<Any>): MutableList<Any> {
        var list = passedList
        while (list.contains('×') || list.contains('÷')) {
            list = calculateTimesDivs(list)
        }

        return list
    }

    private fun calculateTimesDivs(passedList: MutableList<Any>): MutableList<Any> {
        val newList = mutableListOf<Any>()

        var restartIndex = passedList.size

        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex && i < restartIndex) {
                val operator = passedList[i]
                val prev = passedList[i-1] as Float
                val next = passedList[i+1] as Float
                when(operator) {
                    '×' -> {
                        newList.add(prev*next)
                        restartIndex = i+1
                    }
                    '÷' -> {
                        newList.add(prev/next)
                        restartIndex = i+1
                    }
                    else -> {
                        newList.add(prev)
                        newList.add(operator)
                    }
                }

            }
            if (i > restartIndex)
                newList.add(passedList[i])
        }

        return newList

    }

    private fun digitsOps(): MutableList<Any> {
        val calcTV = findViewById<TextView>(R.id.calculations)

        val list = mutableListOf<Any>()
        var currDig = ""
        for(character in calcTV.text) {
            if(character.isDigit() || character == '.') {
                currDig += character
            } else {
                list.add(currDig.toFloat())
                currDig = ""
                list.add(character)
            }
        }

        if (currDig != "") {
            list.add(currDig.toFloat())
        }

        return list
    }

}