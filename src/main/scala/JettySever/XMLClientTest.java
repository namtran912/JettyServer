package JettySever;

/**
 * Created by TuNam1995 on 30/08/2016.
 */
import Model.Person;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class XMLClientTest {

    public static void main(String[] args) {

        WebResource webResource = Client.create().resource("http://localhost:1002/javahonkJetty/person/xml/java");
        ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);

        if (200 != response.getStatus()) {
            System.out.println("Fail to process response : " + response.getStatus());
        }
        Person person = response.getEntity(Person.class);
        System.out.println("Response from server: ");
        System.out.println("Name:"+person.getName());
    }

}