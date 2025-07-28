package com.pet.management.messaging.producer;

import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class VaccineMessageProducer {
    @Resource(lookup = "java:/jms/queue/vaccineQueue")
    private Queue queue;

    @Resource(lookup = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    public void sendMessage(String messageBody) {
        try (JMSContext context = connectionFactory.createContext()) {
            final var message = context.createMessage();
            try {
                message.setJMSType("vaccine.pending");
                message.setObjectProperty("payload", messageBody);
                context.createProducer().send(queue, message);
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
