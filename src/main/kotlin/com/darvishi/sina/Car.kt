package com.darvishi.sina

class Car(var value: String) {
    var index = 0
    var row = 0
    var colomn = 0
    var dir = ' '
    var size = 0
    init {
        if (value.length == 1)
            println("we have $value cars")
        else{
            index = value[0].toString().toInt()
            row = value[2].toString().toInt()
            colomn = value[4].toString().toInt()
            dir = value[6]
            size = value[8].toString().toInt()
        }
    }
}