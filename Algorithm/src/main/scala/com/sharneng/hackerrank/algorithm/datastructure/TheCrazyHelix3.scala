package com.sharneng.hackerrank.algorithm.datastructure

import Operation._

object TheCrazyHelix3 {
    def main(args: Array[String]) {
      TheCrazyHelix.main(new TheCrazyHelix3());
    }
}

class TheCrazyHelix3 extends TheCrazyHelix {
    private var helix: Array[Tuple2[Int, Int]] = null
    private var count = -1
    
    override def run(scanner: java.util.Scanner) = {
      super.run(scanner).par.map(a => a match {
        	case (GetPosition, n: Int, c: Int)  => (GetPosition, n, find(n, 0, c, 1))
        	case (GetValue, p: Int, c: Int) => (GetValue, p, find(p, c, 0, -1))
      	}).toStream
    }
    
    override def init(n: Int, q: Int) {
      helix = new Array[Tuple2[Int, Int]](q)
    }

    def rotate(from :Int, to :Int) {
        if (from < to) {
          count += 1
          helix(count) = Tuple2(from, to)
        }
    }

    def p2e(p: Int): Int = {
      count
    }

    def e2p(n: Int): Int = {
      count
    }

    private def find(q: Int, start: Int, end: Int, step: Int): Int = {
	  var n = q;
	  for(i <- start to end by step) {
	    val h = helix(i)
	    if ( h._1 <= n && n <= h._2) {
	       n = (h._1 + h._2) - n
	    }
	  }
	  n;
    }

}