package com.sharneng.hackerrank

import com.sharneng.UnitSpec

object HackerRankSpec {
  val InputFileExtension = "input"
  val OutputFileExtension = "output"
}

class HackerRankSpec extends UnitSpec {
	protected def getInput(name: String) = getResource(name, HackerRankSpec.InputFileExtension)

	protected def getOutput(name: String) = getResource(name, HackerRankSpec.OutputFileExtension)
	
	protected def getInputStream(name: String) = getResourceAsStream(name, HackerRankSpec.InputFileExtension)

	protected def getOutputStream(name: String) = getResourceAsStream(name, HackerRankSpec.OutputFileExtension)
	
	private def getResource(name: String, ext: String) = {
	  val clazz = getClass
	  clazz.getResource(clazz.getName() + name + "." + ext)
	}
	
	private def getResourceAsStream(name: String, ext: String) = {
	  val clazz = getClass
	  clazz.getResourceAsStream(clazz.getName() + name + "." + ext)
	}
}