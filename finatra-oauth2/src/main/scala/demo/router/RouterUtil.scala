package demo.router


import java.io.{PrintWriter, StringWriter}

import com.twitter.finatra.{Controller, ResponseBuilder}
import com.twitter.util.Future
import demo.util._
import play.api.libs.json.{JsObject, Json, Writes}


trait RouterUtil {
  self: Controller =>

  implicit val ErrorIntWrites: Writes[IntError] = Json.writes[IntError]

  def formatOut(j:JsObject) = Json.stringify(Json.toJson(j))
  def formatOut(j:IntError) = Json.stringify(Json.toJson(j))

  def getStack(e:Throwable) = {
    val errors = new StringWriter()
    e.printStackTrace(new PrintWriter(errors))
    //errors.toString
    s" message: ${e.getMessage} - stack: ${errors.toString}"
  }
  def checkEx: PartialFunction[Throwable, Future[ResponseBuilder]] = {

    case e: IntError => {
      if (e.httpCode == 500) {
        log.error(s" ===> checkEx - IntError - http code:${e.httpCode} - int code: ${e.internalCode} - message:${e.message}")
      }
      render.status(e.httpCode).body(formatOut(e)).contentType("application/json").toFuture
    }

    case e: com.twitter.finagle.CancelledRequestException =>
      log.error(s" ===> checkEx - com.twitter.finagle.CancelledRequestException ===> ${getStack(e)}")
      render.status(500).
      body(formatOut(Errors.genericException("WatermelonServer.output - request cancelled - " + e.toString))).
      contentType("application/json").toFuture
    case e => {
      log.error(s" ===> checkEx - ${getStack(e)}")
      //e.printStackTrace()
      render.status(500).body(formatOut(
        Errors.genericException("WatermelonServer.output generic Exception - " + e.toString + " - "))).contentType("application/json").toFuture
    }
  }
}

