package fr.fxjavadevblog;

import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.MockitoConfig;
import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class ServiceTest {

    @Inject
    Service service;

    @InjectMock
    @MockitoConfig(convertScopes = true)
    EventBus eventBus;

    @BeforeEach
    void setup() {      
        Mockito.when(eventBus.publish(Mockito.anyString(), Mockito.any())).thenReturn(eventBus);
        Mockito.when(eventBus.toString()).thenReturn("Mocked EventBus");
    }

    @Test
    void testService() {
        assertThat(service).isNotNull();     
        assertThat(service.getMessage()).isEqualTo("Hello from Service"); 
        assertThat(service.getEventBus()).isNotNull().hasToString("Mocked EventBus");
    }

    @Test
    void testEventBus() {
        assertThat(eventBus).isNotNull();
        assertThat(eventBus.publish("test", "test")).isNotNull();
        assertThat(eventBus).hasToString("Mocked EventBus");
        Mockito.verify(eventBus).publish("test", "test");
    }

}
