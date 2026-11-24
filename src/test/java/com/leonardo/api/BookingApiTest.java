package com.leonardo.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookingApiTest {
    private int bookingId;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void listarReservas() {
        given()
        .when()
            .get("/booking")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test
    public void crearReserva() {
        String body = """
            {
              "firstname": "Leonardo",
              "lastname": "Perez",
              "totalprice": 150,
              "depositpaid": true,
              "bookingdates": { "checkin": "2026-12-01", "checkout": "2026-12-05" },
              "additionalneeds": "Breakfast"
            }
            """;

        bookingId =
            given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(body)
            .when()
                .post("/booking")
            .then()
                .statusCode(200)
                .body("booking.firstname", equalTo("Leonardo"))
                .extract().path("bookingid");
    }

    @Test(dependsOnMethods = "crearReserva")
    public void consultarReservaCreada() {
        given()
            .header("Accept", "application/json")
            .pathParam("id", bookingId)
        .when()
            .get("/booking/{id}")
        .then()
            .statusCode(200)
            .body("firstname", equalTo("Leonardo"));
    }

    @Test
    public void reservaInexistenteDevuelve404() {
        given()
        .when()
            .get("/booking/999999999")
        .then()
            .statusCode(404);
    }
}