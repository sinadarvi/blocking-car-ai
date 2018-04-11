package com.darvishi.sina.unisearch

import com.darvishi.sina.Car
import com.darvishi.sina.Move

class Node(val map: ArrayList<Car>, val father: Node?, val move: Move?, var depth: Int = 0, var cost:Int = 0)