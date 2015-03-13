package demo.router

import com.twitter.finatra.Controller
import demo.domain.ExampleDomain


class OAuthRouter extends Controller with RouterUtil {
  post("/v1/auth") { req =>
    println(" ========= ROUTER ======")
    ExampleDomain.post(req.contentString) map { u =>
      render.body(formatOut(ExampleDomain.jsonWrap(u))).contentType("application/json")
    } rescue checkEx
  }
}
