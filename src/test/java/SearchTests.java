import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;



public class SearchTests {

    @BeforeEach
    public void configureRestAssured(){
        RestAssured.baseURI = "https://api.rasp.yandex.net/v3.0";
        //RestAssured.basePath = "/search";
        RestAssured.requestSpecification = given()
                .param("apikey", "1df69158-b254-4469-99c3-599794a0b6c5");
    }

    @Test
    public void c146_c145(){
        given()
                //.param("apikey", "1df69158-b254-4469-99c3-599794a0b6c5")
                .spec(requestSpecification)
                .param("from","c146")
                .param("to", "c145")
        .when().get("/search").then().log().all().statusCode(200);
    }

    @Test
    public void wrongStations(){
        given()
                //.param("apikey", "1df69158-b254-4469-99c3-599794a0b6c5")
                .spec(requestSpecification)
                .param("from","c000")
                .param("to", "c001")
                .when().get("/search").then().log().all().statusCode(404);
    }

    @Test
    public void noToParam(){
        given()
                //.param("apikey", "1df69158-b254-4469-99c3-599794a0b6c5")
                .spec(requestSpecification)
                .param("from","c146")
                .when().get("/search").then().log().all().statusCode(400);
    }

    @Test
    public void noFromParam(){
        given()
                //.param("apikey", "1df69158-b254-4469-99c3-599794a0b6c5")
                .spec(requestSpecification)
                .param("to","c64")
                .when().get("/search").then().log().all().statusCode(400);
    }

    @Test
    public void fromKemerovoToNovokuznetskDate13Dec(){
        given()
                //.param("apikey", "1df69158-b254-4469-99c3-599794a0b6c5")
                .spec(requestSpecification)
                .param("from","c64")
                .param("to", "c237")
                .param("transport_types", "train")
                .param("date", "2019-12-13")
                .when().get("/search").then().log().all()
                .statusCode(200)
                .body("segments", empty());
    }

    @Test
    public void wrongDate(){
        given()
                .param("from", "c64")
                .param("to", "c65")
                .param("date", "2019-02-29")
                .when().get("/search").then().log().all()
                .statusCode(400);
    }
}
