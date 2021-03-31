package u05lab

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.function.Executable
import u05lab.code.ExamsManager.{ExamResultFactory, Kind}
import u05lab.code._
class ExamsManagerTest {

  @Test
  def testExamResultBasicBehaviour(){
    // esame fallito, non c'è voto
    assertEquals(ExamResultFactory.failed.kind, Kind.FAILED)
    assertFalse(ExamResultFactory.failed.evaluation.isDefined)
    assertFalse(ExamResultFactory.failed.cumLaude)
    assertEquals(ExamResultFactory.failed.toString(), "FAILED")

    // lo studente si è ritirato, non c'è voto
    assertEquals(ExamResultFactory.retired.kind, Kind.RETIRED)
    assertFalse(ExamResultFactory.retired.evaluation.isDefined)
    assertFalse(ExamResultFactory.retired.cumLaude)
    assertEquals(ExamResultFactory.retired.toString(), "RETIRED")

    //28
    assertEquals(ExamResultFactory.succeeded(28).kind, Kind.SUCCEEDED)
    assertEquals(ExamResultFactory.succeeded(28).evaluation.get, 28)
    assertFalse(ExamResultFactory.succeeded(28).cumLaude)
    assertEquals(ExamResultFactory.succeeded(28).toString(), "SUCCEEDED(28)")

    //30
    assertEquals(ExamResultFactory.succeededCumLaude.kind, Kind.SUCCEEDED)
    assertEquals(ExamResultFactory.succeededCumLaude.evaluation.get, 30)
    assertTrue(ExamResultFactory.succeededCumLaude.cumLaude)
    assertEquals(ExamResultFactory.succeededCumLaude.toString(), "SUCCEEDED(30L)")

  }
}
