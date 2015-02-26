package demo.util




object ServiceConfig {

  import com.typesafe.config.ConfigFactory

  // load application.conf, so we can override with -Dconfig.resource=/absolute/path/production.conf
  private lazy val config = ConfigFactory.load()
  private lazy val service = config.getConfig("service")
  lazy val environment = service.getString("environment")
  lazy val port = service.getInt("port")
  lazy val host = service.getString("host")
  lazy val name = service.getString("name")
  lazy val adminPort = service.getInt("admin-port")
  

  private lazy val queueMsConfig = config.getConfig("queue")
  lazy val queueMsHost = queueMsConfig.getString("host")
  lazy val queueMsPort = queueMsConfig.getInt("port")


}