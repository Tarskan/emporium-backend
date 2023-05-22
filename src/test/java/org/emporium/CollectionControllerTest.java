package org.emporium;

import io.quarkus.test.junit.QuarkusTest;
import org.emporium.model.Collection;
import org.emporium.model.CollectionCreateDTO;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CollectionControllerTest {

    @Test
    public void testAllCollectionEndPoint() {
        given()
                .when().get("/collection")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetCollectionByIdCollectionEndPoint() {
        given()
                .when().get("/collection/7")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetCollectionByIdGenreEndPoint() {
        given()
                .when().get("/collection/genre/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetCollectionByIdEditeurEndPoint() {
        given()
                .when().get("/collection/oeuvres/2")
                .then()
                .statusCode(200);
    }

    /*@Test
    public void testGetCollectionByFavoriteEndPoint() {
        CollectionCreateDTO collectionCreateDTO = CollectionCreateDTO
                .builder()
                .favorite(true)
                .idOeuvre("3")
                .UWUid("2")
                .build();

        given()
                .body(collectionCreateDTO)
                .header("Content-Type", "application/json")
                .when().get("/collection/oeuvres/favorite/2")
                .then()
                .statusCode(200);
    }*/

    @Test
    public void testGetCollectionByIdUserEndPoint() {
        given()
                .when().get("/collection/utilisateur/2")
                .then()
                .statusCode(200);
    }

    @Test
    public void testSuppOeuvresEndPoint() {
        given()
                .when().delete("/collection/delete/6")
                .then()
                .statusCode(200);
    }
}
