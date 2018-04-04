package com.darvishi.sina.uni_search

import com.darvishi.sina.Car

class BFS(val cars: ArrayList<Car>) {
    init {
        cars.forEach {
            println(it.toString())
        }
    }
}