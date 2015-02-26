package demo.filter

import com.twitter.finagle.http.{ Request => FinagleRequest, Response => FinagleResponse}
import com.twitter.finagle.{Service, SimpleFilter}
import com.twitter.finatra.ResponseBuilder

class JsonFilter
  extends SimpleFilter[FinagleRequest, FinagleResponse] with App {

  def apply(
             request: FinagleRequest,
             service: Service[FinagleRequest, FinagleResponse])
   = {
    service(request) map { response =>
      if( request.method == "POST" && request.headers.get("Content-Type")  != "application/json") {
        new ResponseBuilder().plain("Content-Type must be application/json").status(400).build
      } else response
    }
  }
}