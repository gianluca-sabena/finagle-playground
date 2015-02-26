package demo


import com.twitter.finatra._
import demo.router.{ExampleRouter, CommonRouter}
import demo.util.ServiceConfig






object ApiHttpServer extends FinatraServer {

  log.info("==== API Environment: " + ServiceConfig.environment.toString)
  System.setProperty("com.twitter.finatra.config.adminPort", s"${ServiceConfig.host}:${ServiceConfig.adminPort}")
  System.setProperty("com.twitter.finatra.config.port", s"${ServiceConfig.host}:${ServiceConfig.port}")
  System.setProperty("com.twitter.finatra.config.port", s"${ServiceConfig.host}:${ServiceConfig.port}")

  // register routes

  register(new CommonRouter())
  register(new ExampleRouter())
}

