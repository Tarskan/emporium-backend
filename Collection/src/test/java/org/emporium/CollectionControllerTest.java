package org.emporium;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CollectionControllerTest {

    @Test
    public void testUserHaveCollectionEndpoint() {
        given()
          .when().get("/collection/utilisateur/1")
          .then()
             .statusCode(200);
    }

    @Test
    public void testCollectionHaveUserEndpoint() {
        given()
                .when().get("/collection/bibliotheque/1")
                .then()
                .statusCode(200);
    }

}