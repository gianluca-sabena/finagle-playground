//package demo.api.random
//
//
//import com.twitter.finagle.httpx.{Version, Status, Response, Request}
//import com.twitter.finagle.{Service, Httpx}
//import com.twitter.util.{Future, Await}
//
//
//class Respond extends Service[Request, Response] {
//  def apply(request: Request) = {
//    val rep = Response(Version.Http11, Status.Ok)
//    rep.setContentString(s"Hello!")
//    Future.value(rep)
//  }
//}
//
//
//object ApiServerHttpx extends App {
//
//  // Serve the backend using the Httpx protocol.
//  val _ = Await.ready(Httpx.serve(":8080", new Respond))
//
//}