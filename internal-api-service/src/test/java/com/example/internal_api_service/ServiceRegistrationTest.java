package com.example.internal_api_service;

import com.netflix.discovery.EurekaClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;

import static org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "eureka.client.service-url.defaultZone=http://localhost:8761/eureka/",
    "eureka.instance.prefer-ip-address=true"
})
class ServiceRegistrationTest {
    
    @Autowired
    private EurekaClient eurekaClient;
    
    @Test
    void shouldRegisterWithEureka() {
        await().atMost(Duration.ofSeconds(30))
               .until(() -> isServiceRegistered("internal-api-service"));
    }
    
    private boolean isServiceRegistered(String serviceName) {
        return eurekaClient.getInstancesByVipAddress(serviceName, false)
                          .size() > 0;
    }
}