import io.restassured.RestAssured;
import org.junit.Ignore;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CarrierTests {

    @BeforeAll
    public static void configureRestAssured(){
        RestAssured.baseURI = "https://api.rasp.yandex.net/v3.0";
        //RestAssured.basePath = "/search";
        RestAssured.requestSpecification = given()
            .param("apikey", "1df69158-b254-4469-99c3-599794a0b6c5");
    }

    @Test
    public void S7() {
        given()
            .spec(requestSpecification)
            .param("code", "23")
        .when()
            .get("/carrier")
        .then()
            .log().all()
            .statusCode(200)
            .body("carrier.title",is("S7 Airlines"));
    }

    @Test
    public void wrongCode() {
        given()
            .spec(requestSpecification)
            .param("code", "000")
        .when()
            .get("/carrier")
        .then()
            .log().all()
            .statusCode(404);
    }

    @Test
    public void noCode() {
        given()
            .spec(requestSpecification)
        .when()
            .get("/carrier")
        .then()
            .log().all()
            .statusCode(400);
    }
}
