package com.darvishi.sina

data class Move(val whichCar: Int, val dir: Char, val howMuch: Int){
    override fun toString(): String {
        return "$whichCar $dir $howMuch"
    }
}