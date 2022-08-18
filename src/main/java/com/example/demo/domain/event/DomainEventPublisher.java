package com.example.demo.domain.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DomainEventPublisher {
    private static ApplicationEventPublisher publisher;

    @Autowired
    private void setEventPublisher(ApplicationEventPublisher eventPublisher) {
        publisher = eventPublisher;
    }

    public static void publish(Object event) {
        if (publisher == null) {
            throw new IllegalStateException("ApplicationEventPublisher is null. Check configuration");
        }
        publisher.publishEvent(event);
    }
}
