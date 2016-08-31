package JettySever

/**
  * Created by CPU11179-local on 8/31/2016.
  */

import com.sun.jersey.spi.container.servlet.ServletContainer
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder

object JettyServer {
    def run(): Unit = {
      val sh: ServletHolder = new ServletHolder(classOf[ServletContainer])
      sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig")
      sh.setInitParameter("com.sun.jersey.config.property.packages", "RESTful")
      sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true")

      val server: Server = new Server(1002)
      val context: ServletContextHandler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS)
      context.addServlet(sh, "/*")
      server.start()
      server.join()
    }
}
