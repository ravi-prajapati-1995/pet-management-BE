package com.pet.management.messaging.listener;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import lombok.extern.slf4j.Slf4j;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/vaccineQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")})
@Slf4j
public class VaccineMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message.getJMSType().equals("vaccine.pending")) {
                final var payload = message.getObjectProperty("payload");
                log.debug("Payload got with : {}", payload.toString());
//                final Set<PetDetailsDTO> dtos = readValue(payload.toString(), new TypeReference<>() {
//                });
//                dtos.forEach(petDetailsDTO -> log.debug(JsonUtil.asJsonString(petDetailsDTO)));
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
