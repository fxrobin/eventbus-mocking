package fr.fxjavadevblog;


import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Service {

    @Inject
    EventBus eventBus;

    public String getMessage() {
        return "Hello from Service";
    }

    public EventBus getEventBus() {
        return eventBus;
    }

}
