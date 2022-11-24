package com.example.listazadn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class Fragment1 : Fragment(R.layout.fragment_1) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arrayList = ArrayList<String>()
        arrayList.add("Posprzątać pokój")
        arrayList.add("Zrobić zadanie z PUMA")
        arrayList.add("Zjeść obiad")
        arrayList.add("Odpocząć")
        arrayList.add("Umyć się")
        arrayList.add("Iść spać")

        for (item in arrayList) {
            println(item)
        }
    }

}