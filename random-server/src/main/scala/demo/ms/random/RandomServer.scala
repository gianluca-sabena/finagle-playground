package demo.ms.random

import com.twitter.finagle.{Thrift, ThriftMux}
import com.twitter.server.TwitterServer
import com.twitter.util.{Await, Future}
import demo.ms.random.util.ServiceConfig
import demo.thrift.random._
import scala.language.higherKinds

object RandomServer extends TwitterServer {


  // override default admin port
  override def defaultHttpPort = ServiceConfig.serviceAdminPort

  val service = new TRandomNumber[Future] {
    log.info(s"==== Environment: $ServiceConfig.serviceEnvironment")
    val rnd = new scala.util.Random
    def generateNumber(req: TNumberRequest) = {
      val resp:Seq[TNumberResponse] = (1 to req.elements).map(i => {
        val num = req.min + rnd.nextInt((req.max - req.min) + 1)
        TNumberResponse(num,i,req.elements)
      })
      Future.value(resp)
    }
  }

  def main() {

    val server = Thrift.serveIface(s"${ServiceConfig.serviceHost}:${ServiceConfig.servicePort}", service)

    onExit {
      server.close()
    }
    Await.ready(server)
  }
}

