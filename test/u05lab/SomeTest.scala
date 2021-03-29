package u05lab

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._
import u05lab.code._
class SomeTest {

  @Test
  def testIncremental() {
    assert(true)
  }

  @Test
  def testZipRight(): Unit ={
    val l = List("a","b","c")

    assertEquals(List.nil, List.nil.zipRight)
    assertEquals(List(("a",0), ("b",1), ("c",2)), l.zipRight)
  }

  @Test
  def testPartition() = {
    val l = List(1,2,3,4,5)
    val lOk = List(1,2,3)
    val lNo = List(4,5)

    assertEquals((lOk, lNo), l.partition(_<=3))
  }
}