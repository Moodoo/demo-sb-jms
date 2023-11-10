package com.example.demojmsjson.jms;

import com.example.demojmsjson.pojo.BookOrder;
import com.example.demojmsjson.pojo.ProcessedBookOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        jmsTemplate.convertAndSend("book.order.processed.queue", order);
    }
}
