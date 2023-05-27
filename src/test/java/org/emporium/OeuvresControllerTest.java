package org.emporium;

import io.quarkus.test.junit.QuarkusTest;
import org.emporium.model.OeuvresCreateDTO;
import org.emporium.model.OeuvresModifyDTO;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class OeuvresControllerTest {

    @Test
    public void testAllOeuvresEndPoint() {
        given()
                .when().get("/oeuvres")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetOeuvresByIdOeuvresEndPoint() {
        given()
                .when().get("/oeuvres/2")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetOeuvresSearchByTitre() {
        given()
                .when().get("/oeuvres/search/Sonne")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetOeuvresByIdGenreEndPoint() {
        given()
                .when().get("/oeuvres/genre/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetOeuvresByIdEditeurEndPoint() {
        given()
                .when().get("/oeuvres/editeur/2")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetOeuvresByIdAuteurEndPoint() {
        given()
                .when().get("/oeuvres/auteur/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetOeuvresByIdTypeEndPoint() {
        given()
                .when().get("/oeuvres/type/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetOeuvresByIdSupportEndPoint() {
        given()
                .when().get("/oeuvres/support/1")
                .then()
                .statusCode(200);
    }

    /*@Test
    public void testModifyOeuvresEndPoint() {
        OeuvresModifyDTO oeuvresModifyDTO = new OeuvresModifyDTO();
        oeuvresModifyDTO.setIdOeuvre("1");
        oeuvresModifyDTO.setDescription("test");
        oeuvresModifyDTO.setIdAuteur("1");
        oeuvresModifyDTO.setIdGenre("1");
        oeuvresModifyDTO.setIdSupport("2");
        oeuvresModifyDTO.setIdType("1");
        oeuvresModifyDTO.setImage("1");
        oeuvresModifyDTO.setSousTitre("test");
        oeuvresModifyDTO.setTitre("test");

        given()
                .body(oeuvresModifyDTO)
                .header("Content-Type", "application/json")
                .when().put("/oeuvres")
                .then()
                .statusCode(200);
    }

    @Test
    public void testAddOeuvresEndPoint() {
        OeuvresCreateDTO oeuvresCreateDTO = new OeuvresCreateDTO();
        oeuvresCreateDTO.setDescription("test");
        oeuvresCreateDTO.setIdAuteur("1");
        oeuvresCreateDTO.setIdGenre("1");
        oeuvresCreateDTO.setIdSupport("2");
        oeuvresCreateDTO.setIdType("1");
        oeuvresCreateDTO.setImage("1");
        oeuvresCreateDTO.setSousTitre("test");
        oeuvresCreateDTO.setTitre("test");

        given()
                .body(oeuvresCreateDTO)
                .header("Content-Type", "application/json")
                .when().post("/oeuvres")
                .then()
                .statusCode(200);
    }

    @Test
    public void testSuppOeuvresEndPoint() {
        given()
                .when().delete("/oeuvres/delete/1")
                .then()
                .statusCode(200);
    }*/
}
