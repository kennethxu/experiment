package com.sharneng.hackerrank.algorithm.datastructure

import scala.collection.mutable.Buffer

object Operation extends Enumeration {
  type Operation = Value
  val Rotate, GetPosition, GetValue = Value
}

import Operation._

abstract class TheCrazyHelix {
    def run(scanner: java.util.Scanner): Traversable[(Operation, Int, Int)] = {
      val n =scanner.nextInt()
      val q = scanner.nextInt()
      init(n, q)
      for(j <- action(q, scanner) if j != null) yield j
    }

  private def action(q: Int, scanner: java.util.Scanner) = {
      for(i <- TheCrazyHelix.input(q, scanner)) 
        yield i match {
	      	case (Rotate, start: Int, end: Int) => { rotate(start, end); null}
	      	case (GetPosition, n: Int) => (GetPosition, n, e2p(n))
	      	case (GetValue , p: Int) => (GetValue, p, p2e(p))
      	  } 
    }
    
    def init(n: Int, q: Int) = {}

    def rotate(from: Int, to: Int) 
    
    def p2e(p: Int): Int
    
    def e2p(n: Int): Int 
}

object TheCrazyHelix {
	def input(q: Int, scanner: java.util.Scanner) = {
      for(i <- (0 until q).view)
    	yield scanner.nextInt() match {
		  case 1 => (Rotate, scanner.nextInt, scanner.nextInt)
		  case 2 => (GetPosition, scanner.nextInt) 
		  case 3 => (GetValue, scanner.nextInt)
		}
    }

	def output(result: Traversable[Tuple3[Operation, Int, Int]]) = {
	  for (i <- result)
	    yield i match {
        	case (GetPosition, n: Int, p: Int) => "element " + n + " is at position " + p 
        	case (GetValue, p: Int, n: Int) => "element at position " + p + " is " + n
      	}
	}

	def main(helix: TheCrazyHelix) {
      for (s <- output(helix.run(new java.util.Scanner(System.in)))) println(s)
	}
}
