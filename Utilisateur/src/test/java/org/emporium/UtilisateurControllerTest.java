package org.emporium;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UtilisateurControllerTest {

    @Test
    public void testAllUtilisateurEndPoint() {
        given()
          .when().get("/utilisateur")
          .then()
             .statusCode(200);
    }

    @Test
    public void testGetUtilisateurByPseudoEndPoint() {
        given()
                .when().get("/utilisateur/Tarskan")
                .then()
                .statusCode(200);
    }

    @Test
    public void testSearchUtilisateurByPseudoWithCompletionEndPoint() {
        given()
                .when().get("/utilisateur/search/Tarskan")
                .then()
                .statusCode(200);
    }

    @Test
    public void testModifyUtilisateurEndPoint() {
        given()
                .when().put("/utilisateur")
                .then()
                .statusCode(200);
    }

    @Test
    public void testAddUtilisateurEndPoint() {
        given()
                .when().post("/utilisateur")
                .then()
                .statusCode(200);
    }

    @Test
    public void testSuppUtilisateurEndPoint() {
        given()
                .when().delete("/utilisateur/delete/1")
                .then()
                .statusCode(200);
    }

}