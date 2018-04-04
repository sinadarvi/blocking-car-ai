package com.darvishi.sina.unisearch

import com.darvishi.sina.Car
import com.darvishi.sina.Move

class BFS {
    fun ArrayList<Car>.findOneWayOut(work: (ArrayList<Car>) -> ArrayList<Move>): ArrayList<Move> {
        //do BFS
        return work(this)
    }
}
