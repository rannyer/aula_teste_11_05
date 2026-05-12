package com.example.aula_teste_11_05.controller;

import com.example.aula_teste_11_05.models.Animal;
import com.example.aula_teste_11_05.repositories.AnimalRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnimalControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private AnimalRepository animalRepository;

    @BeforeEach
    void setup(){
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
        animalRepository.deleteAll();
    }

    @Test
    void deveSalvarAnimal(){
        String paylod = """
                {
                    "nome": "Rex",
                    "especie": "Cachorro",
                    "cor": "caramelo"
                }
                """;

        given()
                .contentType("application/json")
                .body(paylod)

        .when()
                .post("/animais")
        .then()
                .time(lessThan(2000L))
                .statusCode(200)
                .body("id", notNullValue())
                .body("nome", equalTo("Rex"))
                .body("especie", equalTo("Cachorro"))
                .body("cor", equalTo("caramelo"))
                .log().all()
                ;
    }
    @Test
    void deveListarTodosAnimais(){
        animalRepository.save(new Animal(null, "Rex", "Cachorro", "caramelo"));
        animalRepository.save(new Animal(null, "Miau", "Gato", "preto"));

        given()
        .when()
            .get("/animais")
        .then()
            .statusCode(200)
            .body("size()", equalTo(2))
            .body("[0].nome", equalTo("Rex"))
            .body("[1].nome", equalTo("Miau"))
            .log().all();
    }

    @Test
    void deveRetornarErrorQuandoAnimalNaoExistir(){
        given()
        .when()
            .get("/animais/999")
        .then()
           .statusCode(400)
           .body(equalTo("Animal não encontrado"))
           .log().all();
    }

    @Test
    void deveBuscarAnimalPeloId(){
        Animal animal = animalRepository.save(new Animal(null, "Rex", "Cachorro", "caramelo"));

        given()
        .when()
            .get("/animais/"+animal.getId())
        .then()
            .statusCode(200)
            .body("id", equalTo(animal.getId().intValue()))
            .body("nome", equalTo("Rex"))
            .body("especie", equalTo("Cachorro"))
            .body("cor", equalTo("caramelo"))
            .log().all();
    }


}
