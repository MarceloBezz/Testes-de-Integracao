package br.com.alura.marketplace.iandt.setup;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.utility.DockerImageName;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;
import static org.testcontainers.utility.DockerImageName.parse;

public interface RabbitMQSetup {

    RabbitMQContainer RABBIT_MQ = new RabbitMQContainer(parse("rabbitmq:3.7.25-management-alpine"));

    @DynamicPropertySource
    static void rabbitMQDynamicPropertySource(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.host", RABBIT_MQ::getHost);
        registry.add("spring.rabbitmq.port", RABBIT_MQ::getAmqpPort);
        registry.add("spring.rabbitmq.username", RABBIT_MQ::getAdminUsername);
        registry.add("spring.rabbitmq.password", RABBIT_MQ::getAdminPassword);
    }

    @BeforeAll
    static void RabbitMQBeforeAll() {
        RABBIT_MQ.start();
    }
}
