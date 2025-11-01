package br.com.alura.marketplace.iandt.setup;

import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.utility.DockerImageName;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;
import static org.testcontainers.utility.DockerImageName.parse;

public interface RedisSetup {

    RedisContainer REDIS_CONTAINER = new RedisContainer(parse("redis:6.2.6"));


    @DynamicPropertySource
    static void localstackDynamicPropertySource(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", REDIS_CONTAINER::getHost);
        registry.add("spring.data.redis.port", REDIS_CONTAINER::getRedisPort);
    }

    @BeforeAll
    static void localStackBeforeAll() {
        REDIS_CONTAINER.start();
    }
}
