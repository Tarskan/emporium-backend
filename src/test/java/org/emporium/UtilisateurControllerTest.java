package org.emporium;

import io.quarkus.test.junit.QuarkusTest;
import org.emporium.model.UtilisateurCreateDTO;
import org.emporium.model.UtilisateurModifyDTO;
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
    public void testGetUtilisateurByIdUtilisateurEndPoint() {
        given()
                .when().get("/utilisateur/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testSearchUtilisateurByPseudoEndPoint() {
        given()
                .when().get("/utilisateur/search/Tarskan")
                .then()
                .statusCode(200);
    }

    @Test
    public void testSearchUtilisateurByPseudoWithCompletionEndPoint() {
        given()
                .when().get("/utilisateur/searchLike/Tarskan")
                .then()
                .statusCode(200);
    }

    /*@Test
    public void testModifyUtilisateurEndPoint() {
        UtilisateurModifyDTO utilisateurModifyDTO = new UtilisateurModifyDTO();
        utilisateurModifyDTO.setEquipe("killeur");
        utilisateurModifyDTO.setGrade("Larbinn");
        utilisateurModifyDTO.setPseudo("Yumeko");
        utilisateurModifyDTO.setPwd("test");
        utilisateurModifyDTO.setUWUid("2");
        utilisateurModifyDTO.setResultat("champion");

        given()
                .body(utilisateurModifyDTO)
                .header("Content-Type", "application/json")
                .when().put("/utilisateur")
                .then()
                .statusCode(200);
    }

    @Test
    public void testAddUtilisateurEndPoint() {
        UtilisateurCreateDTO utilisateurCreateDTO = new UtilisateurCreateDTO();
        utilisateurCreateDTO.setPseudo("test");
        utilisateurCreateDTO.setPwd("test");

        given()
                .body(utilisateurCreateDTO)
                .header("Content-Type", "application/json")
                .when().post("/utilisateur")
                .then()
                .statusCode(200);
    }*/

    @Test
    public void testSuppUtilisateurEndPoint() {
        given()
                .when().delete("/utilisateur/delete/1")
                .then()
                .statusCode(200);
    }

}