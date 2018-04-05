package com.darvishi.sina

class Car(value: String) {
    val index = value[0].toString().toInt()
    var row = value[2].toString().toInt()
    var column = value[4].toString().toInt()
    val dir = value[6]
    val size = value[8].toString().toInt()
}