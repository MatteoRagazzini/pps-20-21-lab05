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
//  		assertEquals(em.getAllStudentsFromCall("gennaio"),new HashSet<>(Arrays.asList("rossi","bianchi","verdi","neri")));
//  		assertEquals(em.getAllStudentsFromCall("marzo"),new HashSet<>(Arrays.asList("rossi","bianchi","viola")));
//
//  		// promossi di gennaio con voto
//  		assertEquals(em.getEvaluationsMapFromCall("gennaio").size(),2);
//  		assertEquals(em.getEvaluationsMapFromCall("gennaio").get("verdi"),28);
//  		assertEquals(em.getEvaluationsMapFromCall("gennaio").get("neri"),30);
//  		// promossi di febbraio con voto
//  		assertEquals(em.getEvaluationsMapFromCall("febbraio").size(),2);
//  		assertEquals(em.getEvaluationsMapFromCall("febbraio").get("bianchi"),20);
//  		assertEquals(em.getEvaluationsMapFromCall("febbraio").get("verdi"),30);
//
//  		// tutti i risultati di rossi (attenzione ai toString!!)
//  		assertEquals(em.getResultsMapFromStudent("rossi").size(),3);
//  		assertEquals(em.getResultsMapFromStudent("rossi").get("gennaio"),"FAILED");
//  		assertEquals(em.getResultsMapFromStudent("rossi").get("febbraio"),"FAILED");
//  		assertEquals(em.getResultsMapFromStudent("rossi").get("marzo"),"SUCCEEDED(25)");
//  		// tutti i risultati di bianchi
//  		assertEquals(em.getResultsMapFromStudent("bianchi").size(),3);
//  		assertEquals(em.getResultsMapFromStudent("bianchi").get("gennaio"),"RETIRED");
//  		assertEquals(em.getResultsMapFromStudent("bianchi").get("febbraio"),"SUCCEEDED(20)");
//  		assertEquals(em.getResultsMapFromStudent("bianchi").get("marzo"),"SUCCEEDED(25)");
//  		// tutti i risultati di neri
//  		assertEquals(em.getResultsMapFromStudent("neri").size(),1);
//  		assertEquals(em.getResultsMapFromStudent("neri").get("gennaio"),"SUCCEEDED(30L)");
  	}


}
