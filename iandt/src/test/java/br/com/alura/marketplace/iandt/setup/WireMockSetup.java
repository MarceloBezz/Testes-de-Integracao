package br.com.alura.marketplace.iandt.setup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public interface WireMockSetup {

    WireMockServer WIRE_MOCK = new WireMockServer(9090);

    @BeforeAll
    static void wireMockBeforeAll() {
        WIRE_MOCK.start();
        WireMock.configureFor("localhost", WIRE_MOCK.port());
    }

    @AfterEach
    default void wireMockAfterEach() {
        WIRE_MOCK.resetAll();
    }
}
