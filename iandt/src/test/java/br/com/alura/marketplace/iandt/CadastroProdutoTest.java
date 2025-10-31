package br.com.alura.marketplace.iandt;

import static br.com.alura.marketplace.application.v1.dto.ProdutoDtoFactory.criarProdutoDtoRequest;
import static br.com.alura.marketplace.infra.com.petstore.model.factory.PetDtoFactory.criarPetDto;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import br.com.alura.marketplace.application.Application;
import br.com.alura.marketplace.iandt.setup.LocalStacKSetup;
import br.com.alura.marketplace.iandt.setup.PostgresSetup;
import br.com.alura.marketplace.iandt.setup.RabbitMQSetup;
import br.com.alura.marketplace.iandt.setup.WireMockSetup;
import io.awspring.cloud.s3.S3Template;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
@Testcontainers
public class CadastroProdutoTest implements LocalStacKSetup, WireMockSetup, PostgresSetup, RabbitMQSetup {

    @LocalServerPort
    Integer port;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    S3Template s3Template;

    @Value("${aws.s3.bucket.name}")
    String bucketName;

    @BeforeEach
    void beforeEach() {
        RestAssured.baseURI = "http://localhost:" + port + "/api";

        if(!s3Template.bucketExists(bucketName))
            s3Template.createBucket(bucketName);
    }

    @DisplayName("Quando cadastrar um produto")
    @Nested
    class CadastrarProduto {

        @DisplayName("Então deve cadastrar com sucesso")
        @Nested
        class Sucesso {

            @BeforeEach
            void beforeEach() throws JsonProcessingException {
                var petDto = criarPetDto().comTodosOsCampos();
                WIRE_MOCK.stubFor(post("/petstore/pet")
                        .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(petDto))));
            }

            @DisplayName("Dado um produto com todos os campos")
            @Test
            void teste1() throws JsonProcessingException {
                // Dado
                var produto = criarProdutoDtoRequest().comTodosOsCampos();

                // Quando
                var resposta = RestAssured.given()
                        .log().all()
                        .header("Correlation-id", "a21d161d-df76-453e-9ac2-8dfeb527a63b")
                        .contentType(ContentType.JSON)
                        .body(objectMapper.writeValueAsString(produto))
                        .post("/v1/produtos")
                        .then()
                        .log().all()
                        .extract()
                        .response();

                // Então
                assertThat(resposta.statusCode())
                        .isEqualTo(201);
            }
        }
    }
}
