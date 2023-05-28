package org.emporium;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class GenreControllerTest {
    @Test
    public void testAllGenreEndPoint() {
        given()
                .when().get("/genre")
                .then()
                .statusCode(200);
    }

    /*@Test
    public void testGetGenreByIdGenreEndPoint() {
        given()
                .when().get("/genre/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetGenreSearchByName() {
        given()
                .when().get("/genre/search/Death metal")
                .then()
                .statusCode(200);
    }

    @Test
    public void testModifyGenreEndPoint() {
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
    public void testAddGenreEndPoint() {
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
    public void testSuppGenreEndPoint() {
        given()
                .when().delete("/genre/delete/1")
                .then()
                .statusCode(500);
    }*/
}
