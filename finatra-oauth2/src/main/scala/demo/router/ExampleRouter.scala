package demo.router

import com.twitter.finatra.Controller

import demo.domain.ExampleDomain
import play.api.libs.json.Json

class ExampleRouter extends Controller with RouterUtil {

    get("/v1/example/:txt/fragment") { req =>
      val authToken = req.headers().get("x-auth-token")
      val out = Json.obj("example" ->
        Json.obj(
          "header-x-auth-token" -> authToken,
          "txt" -> req.routeParams.getOrElse("txt","txt not defined").toString,
          "param1" -> req.params.getOrElse("param1","param 1 not defined"))
      )
      render.body(formatOut(out)).contentType("application/json").toFuture
    }

  post("/v1/example") { req =>
    ExampleDomain.post(req.contentString) map { u =>
      render.body(formatOut(ExampleDomain.jsonWrap(u))).contentType("application/json")
    } rescue checkEx
  }

}