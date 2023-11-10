package com.example.demojmsjson.listener;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookOrderProcessingMessageListener implements MessageListener
{
    @Override
    public void onMessage(Message message) {
        try {
           String text= ((TextMessage)message ).getText();
           log.info("message: "+text);
        } catch (JMSException e) {
            log.info(e.getMessage());
        }
    }
}
