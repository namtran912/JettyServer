package RESTful

/**
  * Created by TuNam1995 on 30/08/2016.
  */

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import Model.Person
import Model.Persona

@Path("/javahonkJetty")
class RESTful {
    @GET
    @Path("/person/{name}")
    @Produces(Array(MediaType.TEXT_HTML))
    def getHTMLData(@PathParam("name") name: String): String = name

    @GET
    @Path("/person/xml/{name}")
    @Produces(Array(MediaType.APPLICATION_XML))
    def getXMLData(@PathParam("name") name: String): Person = {
        if (name.equalsIgnoreCase("Java"))
            new Person("Java Honk")
        else
            null //new Person("Some Other Person","NY");
    }

    @GET
    @Path("/person/json/{name}")
    @Produces(Array(MediaType.APPLICATION_JSON))
    def getJASONData(@PathParam("name") name: String): Person = {
        if (name.equalsIgnoreCase("Java"))
            new Person("Java Honk")
        else
            new Person("Some Other Person")
    }

    @POST
    @Path("/postdata/applicationOctetStream")
    @Consumes(Array(MediaType.APPLICATION_OCTET_STREAM))
    def postDataStream(@Context request: HttpServletRequest, @Context response: HttpServletResponse): Response = {
      var body: String = null
      val stringBuilder: StringBuilder = new StringBuilder
      var bufferedReader: BufferedReader = null
      try {
        val inputStream: InputStream = request.getInputStream
        if (inputStream != null) {
          bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
          val charBuffer: Array[Char] = new Array[Char](128)
          var bytesRead: Int = -1
          while ({bytesRead = bufferedReader.read(charBuffer); bytesRead} > 0) stringBuilder.append(charBuffer, 0, bytesRead)
        }
        else stringBuilder.append("")
      }
      catch {
        case ex: IOException => {
          ex.printStackTrace()
        }
      } finally if (bufferedReader != null) try
        bufferedReader.close()

      catch {
        case ex: IOException => {
          ex.printStackTrace()
        }
      }
      body = stringBuilder.toString
      Response.status(200).entity(body + " @@@@ ").build
    }
}

