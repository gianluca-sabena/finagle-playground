package demo


import com.twitter.finatra.test.SpecHelper
import org.scalatest.{Matchers, FlatSpec}
import com.twitter.finatra.{FinatraServer, Controller}

trait FlatSpecHelper extends FlatSpec with Matchers with SpecHelper

class RoutesSpec extends FlatSpecHelper {

  class ExampleApp extends Controller {
    get("/:foo") { request =>
      val decoded = request.routeParams.get("foo").get
      render.plain(decoded).toFuture
    }
  }

  val server = new FinatraServer
  server.register(new ExampleApp)

  "Response" should "contain decoded params" in {
    get("/hello%3Aworld")
    response.body should equal("hello:world")
  }


}



