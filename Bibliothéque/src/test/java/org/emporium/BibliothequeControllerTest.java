package org.emporium;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BibliothequeControllerTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/Bibliotheque")
          .then()
             .statusCode(200);
    }

}