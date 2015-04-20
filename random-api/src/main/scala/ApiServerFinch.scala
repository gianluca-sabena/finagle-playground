package demo.api.random

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.twitter.finagle.{Service, Filter, Httpx}
import com.twitter.server.TwitterServer
import com.twitter.util.{Future, Await}
import io.finch.json.Json

import io.finch.{Endpoint => _, _}
import io.finch.micro._
import io.finch.request._
import io.finch.route.{Endpoint => _, _}



object ApiServerFinch extends App {
  // enable finch-jackson magic
  implicit val objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)

  def getAge(ele: Int): Future[Json] = Future.value(Json.arr(Seq(1,2,3)))
  def getRandom(ele: Int): Future[Json] = RandomClient.generateNumber(ele).map(Json.arr(_))
  val api: Endpoint =
    Get /> Micro.value("Test") |
      Get / "random" /> (param("ele").as[Int] ~~> getRandom)

  Await.ready(Httpx.serve(":9000", api))
}