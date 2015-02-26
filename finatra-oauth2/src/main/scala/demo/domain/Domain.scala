package demo.domain

import com.twitter.finagle.{Thrift, ThriftMux}
import com.twitter.finatra.config
import com.twitter.logging.Logger

import demo.util.{Errors}



trait DomainUtil {
  val log = DomainManager.log

  // check error condition in for comprehension

  def check(res: => Boolean)(err: Throwable): Boolean = {
    if (res) res
    else throw err
  }

  def checkForbidden(res: => Boolean)(errMsg: String): Boolean =
    check(res)(Errors.forbidden(errMsg))

  def checkGenericException(res: => Boolean)(errMsg: String): Boolean =
    check(res)(Errors.genericException(errMsg))

  def checkInvalidData(res: => Boolean)(errMsg: String): Boolean =
    check(res)(Errors.invalidData(errMsg))

}

object DomainManager {

  def log = Logger(config.logNode())

  // val walletMs = Thrift.newIface[WalletMs.FutureIface](s"${ApiConfig.walletMsHostname}:${ApiConfig.walletMsPort}")
  // explicit client - http://twitter.github.io/finagle/guide/FAQ.html#configuring-finagle6
  // val service = Thrift.newService(s"${ApiConfig.walletMsHostname}:${ApiConfig.walletMsPort}")

  //val service = ThriftMux.newService(s"${ServiceConfig.queueMsHost}:${ServiceConfig.queueMsPort}")
  // filter composition <ThriftService>.FinagledClient(<Filter> andThen Thrift/Mux.newService(<dest>))


}