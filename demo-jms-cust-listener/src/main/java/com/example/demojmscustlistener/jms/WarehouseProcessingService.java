package com.example.demojmscustlistener.jms;


import com.example.demojmscustlistener.pojo.BookOrder;
import com.example.demojmscustlistener.pojo.ProcessedBookOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class WarehouseProcessingService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void processOrder(BookOrder bookOrder){
        ProcessedBookOrder order = new ProcessedBookOrder(
                bookOrder,
                new Date(),
                new Date()

        );
        log.info("message resend");
        jmsTemplate.convertAndSend("book.order.processed.queue", order);
    }
}
