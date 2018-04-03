package com.darvishi.sina

class IO{
    fun String.asResource(work: (String) -> Unit) {
        val content = IO::class.java.classLoader.getResource(this).readText()
        work(content)
    }
}
