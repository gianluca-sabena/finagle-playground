package demo.domain

import java.util.{Date, UUID}

import demo.util.{ Errors}
import play.api.libs.json._

import scala.util.{Try, Success, Failure}
import com.twitter.util.{Future}


// data received from api
case class ExampleReq(
                    txt: String)
// data sent to api
case class ExampleRes(
                    txt: String)

object ExampleDomain extends DomainUtil {
  implicit val ExampleReqReads: Reads[ExampleReq] = Json.reads[ExampleReq]
  implicit val ExampleResWrites: Writes[ExampleRes] = Json.writes[ExampleRes]


  def jsonWrap(exRes: ExampleRes) = {
    Json.obj("example" -> exRes)
  }

  def timestamp() = new Date().getTime

  def uuid() = UUID.randomUUID().toString


  def post(body: String): Future[ExampleRes] = {
    Try(Json.parse(body)) match {
      case Success(v) =>
        v.validate[ExampleReq] match {
          case JsSuccess(exampleReq, _) =>
            //
            Future.value(ExampleRes(exampleReq.txt))
          case e: JsError => Future.exception(Errors.invalidData(s"ExampleDomain - (${e.toString})"))
        }
      case Failure(e) => Future.exception(Errors.invalidData(s"ExampleDomain - (${e.toString})"))
    }
  }
}
