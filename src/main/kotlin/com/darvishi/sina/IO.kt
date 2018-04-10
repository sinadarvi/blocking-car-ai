package com.darvishi.sina

import java.io.File

class IO{

    infix fun String.asResource(work: (String) -> Unit) {
        val content = IO::class.java.classLoader.getResource(this).readText()
        work(content)
    }

    fun ArrayList<Move?>.makeActionsFile(){
        File("actions.txt").printWriter().use {
            this.forEach {
                Move -> it.println(Move)
            }
        }
    }
}
