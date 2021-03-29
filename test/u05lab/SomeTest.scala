package u05lab

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.function.Executable
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

  @Test
  def testSpan() = {
    val l = List(1,2,6,3,5)
    val lOk = List(1,2)
    val lNo = List(6,3,5)

    assertEquals((lOk, lNo), l.span(_<=3))
  }

  @Test
  def reduce() = {
    val l = List(2,6,3,5)
    val h: List[Int] = Nil();


    assertThrows(classOf[UnsupportedOperationException], () => h.reduce(_+_))
    assertEquals(16, l.reduce(_+_))
  }
}