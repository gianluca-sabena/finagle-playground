package demo.router

import com.twitter.finatra.Controller
import demo.util.Errors


class CommonRouter extends Controller with RouterUtil {

  // finatra ErrorHandler logs any error, it is annoying  this is why we use rescue checkEx
  //  error { request =>
  //    request.error match {
  //      case Some(t: IntError) => render.status(t.httpCode).body(Json.stringify(Json.toJson(t))).contentType("application/json").toFuture
  //    }
  //  }
  //  get("/") { req =>
  //    render.html("<h1>Watermelon</h1>").toFuture
  //  }


  notFound { request =>
    val errOut = Errors.pathNotFound()
    render.status(errOut.httpCode).body(formatOut(errOut)).contentType("application/json").toFuture
  }

}
