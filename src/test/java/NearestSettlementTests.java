import io.restassured.RestAssured;
import org.junit.Ignore;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class NearestSettlementTests {

    @BeforeAll
    public static void configureRestAssured(){
        RestAssured.baseURI = "https://api.rasp.yandex.net/v3.0";
        //RestAssured.basePath = "/search";
        RestAssured.requestSpecification = given()
            .param("apikey", "1df69158-b254-4469-99c3-599794a0b6c5");
    }

    @Test
    public void Moscow() {
        given()
            .spec(requestSpecification)
            .param("lat", "55.75222")
            .param("lng", "37.61556")
        .when()
            .get("/nearest_settlement")
        .then()
            .log().all()
            .statusCode(200)
            .body(containsString( "Москва"));
    }

    @Test
    public void Novosibirsk(){
        given()
            .spec(requestSpecification)
            .param("lat", "55")
            .param("lng", "83")
        .when()
            .get("/nearest_settlement")
        .then()
            .log().all()
            .statusCode(200)
            .body(containsString( "Новосибирск"));
    }

    @Test
    public void NorthPole(){
        given()
            .spec(requestSpecification)
            .param("lat", "64.751114")
            .param("lng", "-147.349442")
        .when()
            .get("/nearest_settlement")
        .then()
            .log().all()
            .statusCode(404);
    }
}
