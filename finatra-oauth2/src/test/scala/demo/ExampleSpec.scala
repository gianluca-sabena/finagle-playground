package demo


import com.twitter.finatra.test.SpecHelper
import org.scalatest.{SequentialNestedSuiteExecution, Matchers, FlatSpec}
import play.api.libs.json.{Writes, Reads, Format, Json}
import demo.domain.ExampleReq
import demo.util.IntError
import org.apache.commons.codec.binary.Base64

import scala.util.Random


class ExampleSpec extends FlatSpec with Matchers with SpecHelper with SequentialNestedSuiteExecution {
  def log(s: String) = {
    info(" ---> " + s)
  }
  implicit val CustomHttpErrorFormat: Format[IntError] = Json.format[IntError]
  implicit val exampleReqReads: Reads[ExampleReq] = Json.reads[ExampleReq]
  implicit val exampleReqWrites: Writes[ExampleReq] = Json.writes[ExampleReq]
  val server = ApiHttpServer
  var accessToken = ""
  val clientCred = "client_id:client_secret"
  val clientAuth:String = new String(Base64.encodeBase64(clientCred.getBytes),"UTF-8")
  
  "POST /v1/auth" should """respond with 200""" in {
    val check:String = new String(Base64.decodeBase64(clientAuth.getBytes), "UTF-8")
    clientCred should equal(check)
    post(s"/v1/auth", Map("grant_type"->"password","username"->"user","password"->"password"),
    Map("Authorization"->s"Basic $clientAuth"))
    log("Res body: " + response.body)
    log("Res headers: " + response.getHeaders)
    accessToken = (Json.parse(response.body) \ "access_token").as[String]
    accessToken should equal(accessToken)
    //(Json.parse(response.body) \ "example" \ "param1").as[String] should equal("valueParam1")
    response.code should equal(200)
  }

  "GET /v1/example/TXT-value/fragment" should """respond with 400""" in {
    get(s"/v1/example/TXT-value/fragment",Map(), Map())
    response.code should equal(400)
  }

  "GET /v1/example/TXT-value/fragment?param1=valueParam1" should """respond with 200""" in {
    get(s"/v1/example/TXT-value/fragment?param1=valueParam1",
      Map(), Map("Authorization"->s"Bearer $accessToken"))
    log("Res: " + response.body)
    (Json.parse(response.body) \ "example" \ "txt").as[String] should equal("TXT-value")
    (Json.parse(response.body) \ "example" \ "param1").as[String] should equal("valueParam1")
    response.code should equal(200)
  }

  "POST /v1/example" should """respond with 200""" in {
    val str = Random.alphanumeric.take(5).mkString
    val queueReq = ExampleReq(s"Random string: $str")
    // post user
    post("/v1/example", Map(), Map("Content-Type" -> "application/json","Authorization"->s"Bearer $accessToken"),
      Json.stringify(Json.toJson(queueReq)))
    log("User POST - Req - " + Json.stringify(Json.toJson(queueReq)))
    log("User POST - Res - " + response.body)
    val out = (Json.parse(response.body) \ "example" \ "txt").as[String]
    out should equal(queueReq.txt)
    response.code should equal(200)
  }

}








