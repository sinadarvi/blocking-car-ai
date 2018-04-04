package com.darvishi.sina

import com.darvishi.sina.uni_search.BFS

fun main(args: Array<String>) {

    val cars = ArrayList<Car>()

    with(IO()) {
        "./sampleMap.txt".asResource {
            it.lines().forEach {
                if (it.length > 1)
                    cars.add(Car(it))
            }
        }
    }
    BFS(cars)

}

