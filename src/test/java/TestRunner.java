import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.Location;
import pojo.Place;
import pojo.Response;


import java.util.List;
import java.util.Properties;
import utils.PropertiesHandling;

public class TestRunner {


    @Test
    public void getRequest() throws Exception {
        HttpMethods getRequest = new HttpMethods();
        Location location = getRequest.getRequest();

        // Verify status code
        if (getRequest.statusCode != null) {
            Assertions.assertEquals("200", getRequest.statusCode);
            Assertions.assertEquals(String.valueOf(getRequest.contentType),
                    "[application/json]");

            // Verify content
            Assertions.assertNotNull(location, "Location should not be null");
            Assertions.assertEquals("Germany", location.getCountry(), "Country mismatch");
            Assertions.assertEquals("Baden-Württemberg", location.getState(), "State mismatch");
            List<Place> places = location.getPlaces();
            String PostCode = "70597";
            String PlaceName = "Stuttgart Degerloch";
            boolean expectedValueFound = false;
            for (Place place : places) {
                if (PostCode.equals(place.getPostCode()) &&
                        place.getPlaceName().contains(PlaceName)) {
                    expectedValueFound = true;
                    break;
                }
            }

            Assertions.assertTrue(expectedValueFound, "Post code 70597 with place name containing 'Stuttgart Degerloch' not found");
           // Assertions.assertEquals(getRequest.responseTime <= 1000, "Expected response time is below 1 second");
            Assertions.assertTrue(getRequest.responseTime <= 1000, "Expected response time is below 1 second");

        }
        else {
            System.out.println("the request is invalid" + getRequest.statusCode);
        }
    }

    @Test
    public void getRequestAPI2() throws Exception {
        String country = PropertiesHandling.getProperty("country1");
        String postCode = PropertiesHandling.getProperty("postalCode1");
        String placeName1 = PropertiesHandling.getProperty("placeName1");
        String country2 = PropertiesHandling.getProperty("country2");
        String postCode2 = PropertiesHandling.getProperty("postalCode2");
        String placeName2 = PropertiesHandling.getProperty("placeName2");
        String country3 = PropertiesHandling.getProperty("country3");
        String postCode3 = PropertiesHandling.getProperty("postalCode3");
        String placeName3 = PropertiesHandling.getProperty("placeName3");

        HttpMethods getRequest = new HttpMethods();
        Response location = getRequest.getRequestAPI2(country,postCode);
        Response location2 = getRequest.getRequestAPI2(country2,postCode2);
        Response location3 = getRequest.getRequestAPI2(country3,postCode3);

        // Verify status code
        if (getRequest.statusCode != null) {
            Assertions.assertEquals("200", getRequest.statusCode);
            Assertions.assertEquals(String.valueOf(getRequest.contentType),
                    "[application/json]");
            // Verify content
            Assertions.assertNotNull(location, "Location should not be null");
            Assertions.assertEquals("United States", location.getCountry(), "Country mismatch");
            Assertions.assertEquals(postCode, location.getPostCode(), "State mismatch");
            String expectedValueFound=location.getPlaces().getFirst().getPlaceName();
            Assertions.assertEquals(expectedValueFound, placeName1);
            Assertions.assertNotNull(location2, "Location should not be null");
            Assertions.assertEquals("United States", location2.getCountry(), "Country mismatch");
            Assertions.assertEquals(postCode2, location2.getPostCode(), "State mismatch");
            String expectedValueFound2=location2.getPlaces().getFirst().getPlaceName();
            Assertions.assertEquals(expectedValueFound2, placeName2);
            Assertions.assertNotNull(location3, "Location should not be null");
            Assertions.assertEquals("Canada", location3.getCountry(), "Country mismatch");
            Assertions.assertEquals(postCode3, location3.getPostCode(), "State mismatch");
            String expectedValueFound3=location3.getPlaces().getFirst().getPlaceName();
            Assertions.assertEquals(expectedValueFound3, placeName3);
            Assertions.assertTrue(getRequest.responseTime <= 1000, "Expected response time is below 1 second");

        }
        else {
            System.out.println("the request is invalid" + getRequest.statusCode);
        }
    }

    }

