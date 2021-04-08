package u05lab

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.function.Executable
import u05lab.code.ExamsManager.{ExamResultFactory, Kind}
import u05lab.code._
class ExamsManagerTest {

  val em = ExamsManager.ExamsManager;
  val erf = ExamResultFactory

  @Test
  def testExamResultBasicBehaviour(){
    // esame fallito, non c'è voto
    assertEquals(erf.failed.kind, Kind.FAILED)
    assertFalse(erf.failed.evaluation.isDefined)
    assertFalse(erf.failed.cumLaude)
    assertEquals(erf.failed.toString(), "FAILED")

    // lo studente si è ritirato, non c'è voto
    assertEquals(erf.retired.kind, Kind.RETIRED)
    assertFalse(erf.retired.evaluation.isDefined)
    assertFalse(erf.retired.cumLaude)
    assertEquals(erf.retired.toString(), "RETIRED")

    //28
    assertEquals(erf.succeeded(28).kind, Kind.SUCCEEDED)
    assertEquals(erf.succeeded(28).evaluation.get, 28)
    assertFalse(erf.succeeded(28).cumLaude)
    assertEquals(erf.succeeded(28).toString(), "SUCCEEDED(28)")

    //30
    assertEquals(erf.succeededCumLaude.kind, Kind.SUCCEEDED)
    assertEquals(erf.succeededCumLaude.evaluation.get, 30)
    assertTrue(erf.succeededCumLaude.cumLaude)
    assertEquals(erf.succeededCumLaude.toString(), "SUCCEEDED(30L)")
  }

  @Test
  def testPrepareExams(){ //
        em.createNewCall("gennaio");
    		em.createNewCall("febbraio");
    		em.createNewCall("marzo");

    		em.addStudentResult("gennaio", "rossi", erf.failed); // rossi -> fallito
    		em.addStudentResult("gennaio", "bianchi", erf.retired); // bianchi -> ritirato
    		em.addStudentResult("gennaio", "verdi", erf.succeeded(28)); // verdi -> 28
    		em.addStudentResult("gennaio", "neri", erf.succeededCumLaude); // neri -> 30L

    		em.addStudentResult("febbraio", "rossi", erf.failed); // etc..
    		em.addStudentResult("febbraio", "bianchi", erf.succeeded(20));
    		em.addStudentResult("febbraio", "verdi", erf.succeeded(30));

    		em.addStudentResult("marzo", "rossi", erf.succeeded(25));
    		em.addStudentResult("marzo", "bianchi", erf.succeeded(25));
    		em.addStudentResult("marzo", "viola", erf.failed);
  }
      @Test
      def testExamsManagement() {
  		this.testPrepareExams();
//  		// partecipanti agli appelli di gennaio e marzo
  		assertEquals(em.getAllStudentsFromCall("gennaio"),Set("rossi","bianchi","verdi","neri"))
  		assertEquals(em.getAllStudentsFromCall("marzo"),Set("rossi","bianchi","viola"))
//
//  		// promossi di gennaio con voto
  		assertEquals(2, em.getEvaluationsMapFromCall("gennaio").size)
      assertEquals(28, em.getEvaluationsMapFromCall("gennaio")("verdi"))
  		assertEquals(30, em.getEvaluationsMapFromCall("gennaio")("neri"))
//  		// promossi di febbraio con voto
  		assertEquals(2, em.getEvaluationsMapFromCall("febbraio").size)
  		assertEquals(20, em.getEvaluationsMapFromCall("febbraio")("bianchi"))
  		assertEquals(30, em.getEvaluationsMapFromCall("febbraio")("verdi"))
//
//  		// tutti i risultati di rossi (attenzione ai toString!!)
      assertEquals(3, em.getResultsMapFromStudent("rossi").size);
        assertEquals("FAILED", em.getResultsMapFromStudent("rossi")("gennaio"))
  		assertEquals("FAILED", em.getResultsMapFromStudent("rossi")("febbraio"))
  		assertEquals("SUCCEEDED(25)", em.getResultsMapFromStudent("rossi")("marzo"))
//  		// tutti i risultati di bianchi
    	assertEquals(3, em.getResultsMapFromStudent("bianchi").size)
  		assertEquals("RETIRED", em.getResultsMapFromStudent("bianchi")("gennaio"))
  		assertEquals("SUCCEEDED(20)", em.getResultsMapFromStudent("bianchi")("febbraio"))
  		assertEquals("SUCCEEDED(25)", em.getResultsMapFromStudent("bianchi")("marzo"))
//  		// tutti i risultati di neri
  		assertEquals(1, em.getResultsMapFromStudent("neri").size);
  		assertEquals("SUCCEEDED(30L)", em.getResultsMapFromStudent("neri")("gennaio"));
  	}


}
