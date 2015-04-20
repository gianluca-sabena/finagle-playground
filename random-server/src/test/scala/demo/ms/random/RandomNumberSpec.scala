package demo.ms.random

import com.twitter.util.{Await}
import org.scalatest._
import demo.thrift.random._

class RandomNumberSpec extends FlatSpec with Matchers {
  def log(s:String) = {
    info(s" ---> $s")
  }

  "This" should "log" in {
    info("Hello!")
    true should equal(true)
  }

  "RandomNumber" should "generate a list of NumberResponse" in {
    val req = TNumberRequest(0,100,15)
    val res = Await.result(RandomServer.service.generateNumber(req))
    res.length should equal(15)
  }

}