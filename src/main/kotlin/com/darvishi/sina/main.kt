package com.darvishi.sina

fun main(args: Array<String>) {

    val cars = ArrayList<Car>()

    with(IO()) {
        "./sampleMap.txt".asResource {
            it.lines().forEach {
                println(it)
                cars.add(Car(it))
            }
        }
    }

    println(cars.get(0).index)

}

