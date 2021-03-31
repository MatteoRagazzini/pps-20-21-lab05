package u05lab.code

import java.util.concurrent.ArrayBlockingQueue

object ExamsManager extends App {

  /* See: https://bitbucket.org/mviroli/oop2018-esami/src/master/a01b/e1/Test.java */

  trait Kind

  object Kind{
    case object RETIRED extends Kind
    case object FAILED  extends Kind
    case object SUCCEEDED  extends Kind
  }

  trait ExamResults{
    def kind:Kind
    def evaluation: Option[Int]
    def cumLaude:Boolean

    override def toString: String ={
      var text: String = kind.toString
      if(evaluation.isDefined){
        text = text.concat("(" + evaluation.get.toString)
        if(cumLaude) text = text.concat("L")
          text = text.concat(")")
        }
      text
    }
  }

  case class ExamResult
  (kind: Kind, evaluation: Option[Int] = Option.empty, cumLaude: Boolean = false)
    extends ExamResults



  import u05lab.code.ExamsManager.Kind._

  // singleton
  object ExamResultFactory{
    def failed:ExamResult = ExamResult(FAILED)
    def retired:ExamResult = ExamResult(RETIRED)
    def succeeded(evaluation: Int):ExamResult = ExamResult(SUCCEEDED, Some(evaluation))
    def succeededCumLaude:ExamResult = ExamResult(SUCCEEDED, Some(30), cumLaude = true)
  }


    object ExamsManager {
      var exams: Map[String, Map[String, ExamResult]] = ???

      def createNewCall(call:String) = ???

      def addStudentResult(call: String, student: String, result: ExamResult) = ???

      def getAllStudentsFromCall(call: String): Set[String] = ???

      def getEvaluationsMapFromCall(call: String): Map[String, Int] = ???

      def getResultsMapFromStudent(student: String): Map[String, Int] = ???

      def getBestResultFromStudent(student: String): Option[Int] = ???
    }


//  sealed trait ExamsManager {
//    def createNewCall(call:String)
//
//    def addStudentResult(call: String, student: String, result: ExamResult)
//
//    def getAllStudentsFromCall(call: String): Set[String]
//
//    def getEvaluationsMapFromCall(call: String): Map[String, Int]
//
//    def getResultsMapFromStudent(student: String): Map[String, Int]
//
//    def getBestResultFromStudent(student: String): Option[Int]
//  }
//
//
//  abstract case class ExamsManagerImpl() extends ExamsManager{
//    var exams: Map[String, Map[String, ExamResult]]
//
//    override def createNewCall(call: String): Unit = if(!exams.contains(call)) exams + (call -> Map.empty)
//
//    override def addStudentResult(call: String, student: String, result: ExamResult): Unit = ???
//
//    override def getAllStudentsFromCall(call: String): Set[String] = ???
//
//    override def getEvaluationsMapFromCall(call: String): Map[String, Int] = ???
//
//    override def getResultsMapFromStudent(student: String): Map[String, Int] = ???
//
//    override def getBestResultFromStudent(student: String): Option[Int] = ???
//  }
}