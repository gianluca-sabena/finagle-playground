package demo


import com.twitter.finatra.test.SpecHelper
import org.scalatest.{Matchers, FlatSpec}
import play.api.libs.json.{Writes, Reads, Format, Json}
import demo.domain.ExampleReq
import demo.util.IntError

import scala.util.Random


class ExampleSpec extends FlatSpec with Matchers with SpecHelper {
  def log(s: String) = {
    info(" ---> " + s)
  }
  implicit val CustomHttpErrorFormat: Format[IntError] = Json.format[IntError]
  implicit val exampleReqReads: Reads[ExampleReq] = Json.reads[ExampleReq]
  implicit val exampleReqWrites: Writes[ExampleReq] = Json.writes[ExampleReq]
  val server = ApiHttpServer

  "GET /v1/example/TXT-value/fragment?param1=valueParam1" should """respond with 200""" in {
    get(s"/v1/example/TXT-value/fragment?param1=valueParam1", Map(), Map())
    log("Res: " + response.body)
    (Json.parse(response.body) \ "example" \ "txt").as[String] should equal("TXT-value")
    (Json.parse(response.body) \ "example" \ "param1").as[String] should equal("valueParam1")
    response.code should equal(200)
  }

  "POST /v1/example" should """respond with 200""" in {
    val str = Random.alphanumeric.take(5).mkString
    val queueReq = ExampleReq(s"Random string: $str")
    // post user
    post("/v1/example", Map(), Map("Content-Type" -> "application/json"), Json.stringify(Json.toJson(queueReq)))
    log("User POST - Req - " + Json.stringify(Json.toJson(queueReq)))
    log("User POST - Res - " + response.body)
    val out = (Json.parse(response.body) \ "example" \ "txt").as[String]
    out should equal(queueReq.txt)
    response.code should equal(200)
  }

}








