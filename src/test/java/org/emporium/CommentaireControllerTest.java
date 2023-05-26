package org.emporium;

import io.quarkus.test.junit.QuarkusTest;
import org.emporium.model.CommentaireModifyDTO;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CommentaireControllerTest {
    /*@Test
    public void testAllUtilisateurEndPoint() {
        given()
                .when().get("/commentaire")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetCommentaireByIdCommentaireEndPoint() {
        given()
                .when().get("/commentaire/5")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetCommentaireByUwuidEndPoint() {
        given()
                .when().get("/commentaire/utilisateur/3")
                .then()
                .statusCode(200);
    }


    /*@Test
    public void testModifyCommentaireEndPoint() {
        CommentaireModifyDTO commentaireModifyDTO = new CommentaireModifyDTO();
        commentaireModifyDTO.setText("Yumeko");
        commentaireModifyDTO.setIdOeuvre("1");
        commentaireModifyDTO.setUWUid("2");
        commentaireModifyDTO.setIdCommentaire("1");

        given()
                .body(commentaireModifyDTO)
                .header("Content-Type", "application/json")
                .when().put("/commentaire")
                .then()
                .statusCode(200);
    }

    @Test
    public void testAddCommentaireEndPoint() {
        UtilisateurCreateDTO utilisateurCreateDTO = new UtilisateurCreateDTO();
        utilisateurCreateDTO.setPseudo("test");
        utilisateurCreateDTO.setPwd("test");

        given()
                .body(utilisateurCreateDTO)
                .header("Content-Type", "application/json")
                .when().post("/utilisateur")
                .then()
                .statusCode(200);
    }

    @Test
    public void testSuppCommentaireEndPoint() {
        given()
                .when().delete("/commentaire/delete/5")
                .then()
                .statusCode(200);
    }*/
}
