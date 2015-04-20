package demo.ms.random.util



object ServiceConfig {
  import com.typesafe.config.ConfigFactory
  // load application.conf, so we can override with -Dconfig.resource=/absolute/path/production.conf
  lazy val config = ConfigFactory.load()
  val service = config.getConfig("service")
  val servicePort = service.getInt("port")
  val serviceHost = service.getString("host")
  val serviceAdminPort = service.getInt("admin-port")
  val serviceEnvironment = service.getString("environment")


}

