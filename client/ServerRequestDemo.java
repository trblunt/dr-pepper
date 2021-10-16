import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*
 * This is a simple script that demonstrates making a call to the server, by 
 * sending a GET request to the time endpoint, which returns the current time 
 * from the PG database.
 */

public class ServerRequestDemo {
	
	static String endpointLocation = "http://50.116.40.94:3000";

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Making Request...");
		
		try {
	        HttpClient client = HttpClient.newHttpClient();
	        HttpRequest timeRequest = HttpRequest.newBuilder()
	                .uri(URI.create(endpointLocation + "/time"))
	                .GET() // GET is default
	                .build();

	        HttpResponse<String> timeResponse = client.send(timeRequest, HttpResponse.BodyHandlers.ofString());

	        System.out.println(timeResponse.statusCode());
	        System.out.println(timeResponse.headers());
	        System.out.println(timeResponse.body());

		}  catch (Exception e) {
			System.out.println("Request Failed: " + e.getMessage());
		}

	}
}
