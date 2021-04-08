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
      var map: Map[String, Map[String, ExamResult]] = Map.empty[String, Map[String, ExamResult]]

      def createNewCall(call:String) = map = map + (call -> Map.empty[String, ExamResult])

      def addStudentResult(call: String, student: String, result: ExamResult) = {
        if(map.contains(call)){
          // io l'avrei fatto così
//          map = map + (map(call) + (student -> result))
          map = map + (call -> (map(call) + (student -> result)))
        }
      }

      def getAllStudentsFromCall(call: String): Set[String] = map(call).keySet

      def getEvaluationsMapFromCall(call: String): Map[String, Int] = map(call).filter(m => m._2.evaluation.isDefined)  mapValues(er => er.evaluation.get)

      def getResultsMapFromStudent(student: String): Map[String, String] = map.filter(s=>s._2.contains(student)).map(s => s._1 -> s._2(student).toString)

      def getBestResultFromStudent(student: String): Option[Int] = Some(map.filter(s=>s._2.contains(student) && s._2(student).evaluation.isDefined)
        .map(s=> s._2(student).evaluation.get).max)
    }
}