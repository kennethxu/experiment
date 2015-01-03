package com.sharneng.hackerrank.algorithm.datastructure

import scala.collection.mutable.Buffer

object TheCrazyHelix2 {
    def main(args: Array[String]) {
      TheCrazyHelix.main(new TheCrazyHelix2());
    }
}

class TheCrazyHelix2 extends TheCrazyHelix {
    var positions : Array[Int] = null;
    var numbers : Array[Int] = null;
    
    override def init(size: Int, q: Int) = {
        positions = new Array[Int](size+1);
        numbers = new Array[Int](size+1);
        for( i <- 1 to size) {
            positions(i) = i;
            numbers(i) = i;
        }
    }

    def rotate(from :Int, to :Int) {
      var f = from
      var t = to
      while (f < t) {
        val temp = numbers(f)
        numbers(f) = numbers(t)
        numbers(t) = temp
        positions(numbers(f)) = f
        positions(numbers(t)) = t
        f += 1
        t -= 1
      }
    }

    def p2e(p: Int): Int = numbers(p)

    def e2p(n: Int): Int = positions(n)

}