package u05lab.code

object ExamsManagerTest extends App {

  /* See: https://bitbucket.org/mviroli/oop2018-esami/src/master/a01b/e1/Test.java */

  sealed trait ExamsResult{
    trait kind
    case class RETIRED() extends kind
    case class FAILED() extends kind
    case class SUCCEEDED() extends kind

    def getKind: kind

    def getEvaluation: Option[Int]

    def cumLaude: Boolean
  }

  sealed trait ExamResultFactory{
    def failed:ExamsResult
    def retired:ExamsResult
    def succeededCumLaude:ExamsResult
    def succeeded(evaluation: Int):ExamsResult
  }

  sealed trait ExamsManager {
    def createNewCall(call:String)

    def addStudentResult(call: String, student: String, result: ExamsResult)

    def getAllStudentsFromCall(call: String): Set[String]

    def getEvaluationsMapFromCall(call: String): Map[String, Int]

    def getResultsMapFromStudent(student: String): Map[String, Int]

    def getBestResultFromStudent(student: String): Option[Int]
  }

  case class ExamResultFactoryImpl() extends ExamResultFactory{

    override def failed: ExamsResult = new ExamsResult {
      override def getKind: kind = FAILED()

      override def getEvaluation: Option[Int] = Option.empty

      override def cumLaude: Boolean = false
    }

    override def retired: ExamsResult = new ExamsResult {
      override def getKind: kind = RETIRED()

      override def getEvaluation: Option[Int] = Option.empty

      override def cumLaude: Boolean = false
    }

    override def succeededCumLaude: ExamsResult = new ExamsResult {
      override def getKind: kind = SUCCEEDED()

      override def getEvaluation: Option[Int] = Option[30]

      override def cumLaude: Boolean = true
    }

    override def succeeded(evaluation: Int): ExamsResult = new ExamsResult {
      override def getKind: kind = SUCCEEDED()

      override def getEvaluation: Option[Int] = Option[30]

      override def cumLaude: Boolean = false
    }
  }

  abstract case class ExamManagerImpl() extends ExamsManager{
    var exams: Map[String, List[(String, ExamsResult)]]

    override def createNewCall(call: String): Unit = ???

    override def addStudentResult(call: String, student: String, result: ExamsResult): Unit = ???

    override def getAllStudentsFromCall(call: String): Set[String] = ???

    override def getEvaluationsMapFromCall(call: String): Map[String, Int] = ???

    override def getResultsMapFromStudent(student: String): Map[String, Int] = ???

    override def getBestResultFromStudent(student: String): Option[Int] = ???
  }



}