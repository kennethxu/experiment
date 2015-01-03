package com.sharneng.hackerrank.algorithm.datastructure

import scala.collection.mutable.Buffer
import scala.collection.JavaConverters._

object TheCrazyHelix1 {
    def main(args: Array[String]) {
      TheCrazyHelix.main(new TheCrazyHelix1());
    }
}

class TheCrazyHelix1 extends TheCrazyHelix {
    val buf = Buffer.empty[(Int, Int)]

    def rotate(from :Int, to :Int) {
        if (from < to) buf += Tuple2(from, to)
    }

    def p2e(p: Int): Int = {
      find(p, buf.reverseIterator)
    }

    def e2p(n: Int): Int = {
      find(n, buf)
    }

    private def find(i: Int, helix: TraversableOnce[(Int, Int)]): Int = {
	  var n = i;
	  for(a <- helix)
	    if ( a._1 <= n && n <= a._2) {
	       n = (a._1 + a._2) - n
	    }
	  n;
    }
}