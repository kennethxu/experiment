package com.sharneng.hackerrank.algorithm.datastructure

import com.sharneng.UnitSpec
import Operation._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import org.mockito.Matchers
import java.util.Scanner
import scala.io.Source
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class TheCrazyHelixTest extends UnitSpec with MockitoSugar with ShouldMatchers {
  
  "input" should "scan the input and convert into operations" in {
    val source = """ 
      1 11 12
      2 22
      2 23
      1 13 14
      3 31
      1 15 16
      2 24
      3 32"""

    val expected = List(
      (Rotate, 11, 12), 
      (GetPosition, 22), 
      (GetPosition, 23), 
      (Rotate, 13, 14), 
      (GetValue, 31), 
      (Rotate, 15, 16), 
      (GetPosition, 24),
      (GetValue, 32))
      
    val result = TheCrazyHelix.input(8, new java.util.Scanner(source))
    
    result should contain theSameElementsInOrderAs expected
  }
  
  "output" should "convert query result to text" in {
    val source = List( 
      (GetPosition, 1, 4), 
      (GetPosition, 5, 2), 
      (GetValue, 4, 1), 
      (GetPosition, 3, 5),
      (GetValue, 2, 5))

    val expected = List(
        "element 1 is at position 4",
        "element 5 is at position 2",
        "element at position 4 is 1",
        "element 3 is at position 5",
        "element at position 2 is 5")

    val result = TheCrazyHelix.output(source)
    
    result should be (expected)
  }
  
  "run" should "manage lifecycle of method calls" in {
    val source = """9 4
      2 22
      1 15 16
      3 31
      2 24"""

    val expected = Vector(
        (GetPosition, 22, 23),
        (GetValue, 31, 32),
        (GetPosition, 24, 25))
        
    val sut = mock[TheCrazyHelix]
    when(sut.e2p(22)).thenReturn(23)
    when(sut.e2p(24)).thenReturn(25)
    when(sut.p2e(31)).thenReturn(32)
    when(sut.run(Matchers.any(classOf[Scanner]))).thenCallRealMethod()
    
    val result = sut.run(new java.util.Scanner(source))
    
    result should be (expected)
    
    verify(sut).rotate(15, 16)
    verify(sut).init(9, 4)
  }
}

abstract class TheCrazyHelixSpec extends UnitSpec {
	def spec(helix: TheCrazyHelix, caseNo: Int) = {
	  val filename = "TheCrazyHelixTestCase"
	  val input = new Scanner(getClass.getResourceAsStream(filename + caseNo + ".input"))
	  val expected = for(i <- Source.fromURL(getClass.getResource(filename + caseNo +".output")).getLines) 
	    yield i.substring(i.lastIndexOf(' ')+1).toInt

	  val result = helix.run(input).map(_._3)

	  for ((r, e) <- result.toIterator zip expected) r should be (e)
	}

  
	"helix" should "pass test case 0" in spec(sut, 0)

	it should "pass test case 1" in spec(sut, 1)

	it should "pass test case 5" in spec(sut, 5)
	
	it should "pass test case 11" in spec(sut, 11)


	protected def sut: TheCrazyHelix
}