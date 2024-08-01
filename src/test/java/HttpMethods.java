import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import pojo.Location;
import pojo.Response;
import utils.PropertiesHandling;

public class HttpMethods {

    public String statusCode;
    public String contentType;
    public long responseTime;

    // HTTP client
    HttpClient client = HttpClient.newHttpClient();


    public Location getRequest() throws Exception {

        // creating the request
        System.out.println("The Request is " + " GET//" + PropertiesHandling.getProperty("get.employee.url"));
        HttpRequest requestGet = HttpRequest.newBuilder()
                .GET()
                .timeout(Duration.ofSeconds(10))
                .uri(URI.create(PropertiesHandling.getProperty("get.employee.url")))
                .build();

        // sending the request
        HttpResponse<String> response;
        Location getPlace;


        Instant start;
        Instant end;
        {
            try {
                start = Instant.now();
                response = client.send(requestGet, HttpResponse.BodyHandlers.ofString());
                end = Instant.now();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        responseTime = Duration.between(start, end).toMillis();
        contentType = String.valueOf(response.headers().allValues("Content-Type"));
        statusCode = String.valueOf(response.statusCode());

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        getPlace = gson.fromJson(response.body(), Location.class);
        return getPlace;

    }
    public Response getRequestAPI2(String country , String PosCode) throws Exception {

        // creating the request
        System.out.println("The Request is " + " GET//"+ PropertiesHandling.getProperty("api2.url")+  country + "/" + PosCode);
        HttpRequest requestGet = HttpRequest.newBuilder()
                .GET()
                .timeout(Duration.ofSeconds(10))
                .uri(URI.create(PropertiesHandling.getProperty("api2.url")+  country + "/" + PosCode))
                .build();

        // sending the request
        HttpResponse<String> response;
        Response getPlace;
        Instant start;
        Instant end;

        {
            try {
                start = Instant.now();
                response = client.send(requestGet, HttpResponse.BodyHandlers.ofString());
                end = Instant.now();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
        }
            responseTime = Duration.between(start, end).toMillis();
            contentType =String.valueOf(response.headers().allValues("Content-Type"));
            Assertions.assertTrue(response.request().timeout().isPresent() && response.request().timeout().get().toMillis() >= 10000, "Expected response time is below 1 second");
            statusCode = String.valueOf(response.statusCode());
            Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        getPlace = gson.fromJson(response.body(), Response.class);
        return getPlace;

    }

}
}


