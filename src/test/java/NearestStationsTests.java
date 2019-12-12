import io.restassured.RestAssured;
import org.junit.Ignore;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class NearestStationsTests {

    @BeforeEach
    public void configureRestAssured(){
        RestAssured.baseURI = "https://api.rasp.yandex.net/v3.0";
        //RestAssured.basePath = "/search";
        RestAssured.requestSpecification = given()
                .param("apikey", "1df69158-b254-4469-99c3-599794a0b6c5");
    }

    @Test
    public void Moscow() {
        given()
                .spec(requestSpecification)
                .param("lat", "56")
                .param("lng", "37")
                .param("distance", "50")
                .param("transport_types", "plane")
                .when().get("/nearest_stations")
                .then().log().all()
                .statusCode(200)
                .body(containsString( "Внуково"));
    }

    @Test
    public void Novosibirsk(){
        given()
                .spec(requestSpecification)
                .param("lat", "55")
                .param("lng", "83")
                .param("distance", "50")
                .param("transport_types", "plane")
                .when().get("/nearest_stations")
                .then().log().all()
                .statusCode(200)
                .body(containsString("Толмачёво"));
    }


}
