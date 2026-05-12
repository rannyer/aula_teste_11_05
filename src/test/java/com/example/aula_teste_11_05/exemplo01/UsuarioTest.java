package com.example.aula_teste_11_05.exemplo01;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioTest {

    @Test
    void deveBuscarUsuario(){
        given()

        .when()
            .get("https://jsonplaceholder.typicode.com/users/1")

        .then()
            .statusCode(200)
            .body("name",  equalTo("Leanne Graham"))
            .body("username", equalTo("Bret"));

    }
    @Test
    void deveExibirResposta(){

        when()
                .get("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .log().all();
    }
}
