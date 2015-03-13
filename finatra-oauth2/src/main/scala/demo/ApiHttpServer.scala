package demo

import demo.filter._
import com.twitter.finagle.oauth2._
import com.twitter.finatra._
import demo.router.{ExampleRouter, CommonRouter, OAuthRouter}
import demo.util.ServiceConfig
import demo.domain._

object ApiHttpServer extends FinatraServer {
  log.info("==== API Environment: " + ServiceConfig.environment.toString)
  System.setProperty("com.twitter.finatra.config.adminPort", s"${ServiceConfig.host}:${ServiceConfig.adminPort}")
  System.setProperty("com.twitter.finatra.config.port", s"${ServiceConfig.host}:${ServiceConfig.port}")
  System.setProperty("com.twitter.finatra.config.port", s"${ServiceConfig.host}:${ServiceConfig.port}")
  val dataHandler = new OAuthDataHandler
  val authFilter = new MyOAuth2Filter(dataHandler) with OAuthErrorInJson
  // register routes
  addFilter(authFilter)
  register(new CommonRouter())
  register(new ExampleRouter())
  register(new OAuthRouter())
}

