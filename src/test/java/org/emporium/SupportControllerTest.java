package org.emporium;

import io.quarkus.test.junit.QuarkusTest;
import org.emporium.model.OeuvresModifyDTO;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SupportControllerTest {
    @Test
    public void testAllSupportEndPoint() {
        given()
                .when().get("/support")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetGenreByIdSupportEndPoint() {
        given()
                .when().get("/support/2")
                .then()
                .statusCode(200);
    }

    /*@Test
    public void testGetSupportSearchByName() {
        given()
                .when().get("/support/search/CD")
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
    public void testSuppSupportEndPoint() {
        given()
                .when().delete("/support/delete/1")
                .then()
                .statusCode(200);
    }*/
}
