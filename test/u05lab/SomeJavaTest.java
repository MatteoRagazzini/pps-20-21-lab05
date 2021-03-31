//package u05lab;
//
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import u05lab.code.ExamsManagerTest;
//import u05lab.code.ExamsManagerTest.ExamResultFactory;
//import u05lab.code.ExamsManagerTest.ExamsManager;
//import u05lab.code.ExamsManagerTest.ExamResultFactoryImpl;
//import u05lab.code.ExamsManagerTest.ExamsManagerImpl;
//import u05lab.code.ExamsManagerTest.ExamResult;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class SomeJavaTest {
//
//	private ExamResultFactory erf = new ExamResultFactoryImpl();
//	private ExamsManager em = new ExamsManagerImpl();
//
//	// verifica base di ExamResultFactory
//	@Test
//    public void testExamResultsBasicBehaviour() {
//		// esame fallito, non c'è voto
//		assertEquals(erf.failed().getKind(), new ExamResult.Kind.FAILED());
//		assertFalse(erf.failed().getEvaluation().isDefined());
//		assertFalse(erf.failed().cumLaude());
//		assertEquals(erf.failed().toString(), "FAILED");
//
//		// lo studente si è ritirato, non c'è voto
//		assertEquals(erf.retired().getKind(), new ExamResult.Kind.RETIRED());
//		assertFalse(erf.retired().getEvaluation().isDefined());
//		assertFalse(erf.retired().cumLaude());
//		assertEquals(erf.retired().toString(), "RETIRED");
//
//		// 30L
//		assertEquals(erf.succeededCumLaude().getKind(), new ExamResult.Kind.SUCCEEDED());
//		assertEquals(erf.succeededCumLaude().getEvaluation(), Optional.of(30));
//		assertTrue(erf.succeededCumLaude().cumLaude());
//		assertEquals(erf.succeededCumLaude().toString(), "SUCCEEDED(30L)");
//
//		// esame superato, ma non con lode
//		assertEquals(erf.succeeded(28).getKind(),new  ExamResult.Kind.SUCCEEDED());
//		assertEquals(erf.succeeded(28).getEvaluation(), Optional.of(28));
//		assertFalse(erf.succeeded(28).cumLaude());
//		assertEquals(erf.succeeded(28).toString(), "SUCCEEDED(28)");
//    }
//
//	// verifica eccezione in ExamResultFactory
//	@org.junit.Test(expected = IllegalArgumentException.class)
//    public void optionalTestEvaluationCantBeGreaterThan30() {
//		erf.succeeded(32);
//    }
//
//	// verifica eccezione in ExamResultFactory
//	@org.junit.Test(expected = IllegalArgumentException.class)
//    public void optionalTestEvaluationCantBeSmallerThan18() {
//		erf.succeeded(17);
//    }
//
//	// metodo di creazione di una situazione di risultati in 3 appelli
//	private void prepareExams() {
//		em.createNewCall("gennaio");
//		em.createNewCall("febbraio");
//		em.createNewCall("marzo");
//
//		em.addStudentResult("gennaio", "rossi", erf.failed()); // rossi -> fallito
//		em.addStudentResult("gennaio", "bianchi", erf.retired()); // bianchi -> ritirato
//		em.addStudentResult("gennaio", "verdi", erf.succeeded(28)); // verdi -> 28
//		em.addStudentResult("gennaio", "neri", erf.succeededCumLaude()); // neri -> 30L
//
//		em.addStudentResult("febbraio", "rossi", erf.failed()); // etc..
//		em.addStudentResult("febbraio", "bianchi", erf.succeeded(20));
//		em.addStudentResult("febbraio", "verdi", erf.succeeded(30));
//
//		em.addStudentResult("marzo", "rossi", erf.succeeded(25));
//		em.addStudentResult("marzo", "bianchi", erf.succeeded(25));
//		em.addStudentResult("marzo", "viola", erf.failed());
//	}
//
//	// verifica base della parte obbligatoria di ExamManager
//	@org.junit.Test
//    public void testExamsManagement() {
//		this.prepareExams();
//		// partecipanti agli appelli di gennaio e marzo
//		assertEquals(em.getAllStudentsFromCall("gennaio"),new HashSet<>(Arrays.asList("rossi","bianchi","verdi","neri")));
//		assertEquals(em.getAllStudentsFromCall("marzo"),new HashSet<>(Arrays.asList("rossi","bianchi","viola")));
//
//		// promossi di gennaio con voto
//		assertEquals(em.getEvaluationsMapFromCall("gennaio").size(),2);
//		assertEquals(em.getEvaluationsMapFromCall("gennaio").get("verdi"),28);
//		assertEquals(em.getEvaluationsMapFromCall("gennaio").get("neri"),30);
//		// promossi di febbraio con voto
//		assertEquals(em.getEvaluationsMapFromCall("febbraio").size(),2);
//		assertEquals(em.getEvaluationsMapFromCall("febbraio").get("bianchi"),20);
//		assertEquals(em.getEvaluationsMapFromCall("febbraio").get("verdi"),30);
//
//		// tutti i risultati di rossi (attenzione ai toString!!)
//		assertEquals(em.getResultsMapFromStudent("rossi").size(),3);
//		assertEquals(em.getResultsMapFromStudent("rossi").get("gennaio"),"FAILED");
//		assertEquals(em.getResultsMapFromStudent("rossi").get("febbraio"),"FAILED");
//		assertEquals(em.getResultsMapFromStudent("rossi").get("marzo"),"SUCCEEDED(25)");
//		// tutti i risultati di bianchi
//		assertEquals(em.getResultsMapFromStudent("bianchi").size(),3);
//		assertEquals(em.getResultsMapFromStudent("bianchi").get("gennaio"),"RETIRED");
//		assertEquals(em.getResultsMapFromStudent("bianchi").get("febbraio"),"SUCCEEDED(20)");
//		assertEquals(em.getResultsMapFromStudent("bianchi").get("marzo"),"SUCCEEDED(25)");
//		// tutti i risultati di neri
//		assertEquals(em.getResultsMapFromStudent("neri").size(),1);
//		assertEquals(em.getResultsMapFromStudent("neri").get("gennaio"),"SUCCEEDED(30L)");
//
//	}
//
//	// verifica del metodo ExamManager.getBestResultFromStudent
//	@org.junit.Test
//    public void optionalTestExamsManagement() {
//		this.prepareExams();
//		// miglior voto acquisito da ogni studente, o vuoto..
//		assertEquals(em.getBestResultFromStudent("rossi"),Optional.of(25));
//		assertEquals(em.getBestResultFromStudent("bianchi"),Optional.of(25));
//		assertEquals(em.getBestResultFromStudent("neri"),Optional.of(30));
//		assertEquals(em.getBestResultFromStudent("viola"), Optional.empty());
//	}
//
//
//	@org.junit.Test(expected = IllegalArgumentException.class)
//    public void optionalTestCantCreateACallTwice() {
//		this.prepareExams();
//		em.createNewCall("marzo");
//    }
//
//	@org.junit.Test(expected = IllegalArgumentException.class)
//    public void optionalTestCantRegisterAnEvaluationTwice() {
//		this.prepareExams();
//		em.addStudentResult("gennaio", "verdi", erf.failed());
//    }
//
//}
