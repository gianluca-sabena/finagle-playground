package demo.api.random

import com.twitter.finagle.{Thrift, ThriftMux}
import com.twitter.util.Future
import demo.thrift.random.{TMsError, TNumberRequest}
import demo.thrift.random.TRandomNumber.FinagledClient


object RandomClient {
  private val service = Thrift.newService(s"${ServiceConfig.serviceHost}:${ServiceConfig.servicePort}")
  private val randomServerMs = new FinagledClient(service = service, serviceName = "random-server-ms")

  def generateNumber(elements: Int): Future[Seq[Int]] = {
    randomServerMs.generateNumber(TNumberRequest(0, 100, elements)).flatMap(e => {
      val arr = e.map(_.num)
      println(s" Random array: ${arr.toString()}")
     //Future.value(arr.toSeq)
      Future.value(arr)
    }).rescue({
      case e => {
        println(s"Random number ms error - $e")
        Future.exception(TMsError(1, Some(e.toString)))
      }
    })
  }
}