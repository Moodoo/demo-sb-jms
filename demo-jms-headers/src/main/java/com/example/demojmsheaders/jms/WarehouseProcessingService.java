package com.example.demojmsheaders.jms;


import com.example.demojmsheaders.pojo.BookOrder;
import com.example.demojmsheaders.pojo.ProcessedBookOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import java.util.Date;

@Slf4j
//@Service
public class WarehouseProcessingService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void processOrder(BookOrder bookOrder, String orderState, String storeId){
        ProcessedBookOrder order = new ProcessedBookOrder(
                bookOrder,
                new Date(),
                new Date()

        );
        if("NEW".equalsIgnoreCase(orderState)){
            add(bookOrder, storeId);
        } else  if("UPDATE".equalsIgnoreCase(orderState)){
            update(bookOrder, storeId);
        } else if ("DELETE".equalsIgnoreCase(orderState)){
            delete(bookOrder, storeId);
        }
        log.info("message resend");
        jmsTemplate.convertAndSend("book.order.processed.queue", order);
    }
    private void add(BookOrder bookOrder, String storeId){
        log.info("ADDING A NEW ORDER TO THE DB");
    }
    private void update(BookOrder bookOrder, String storeId){
        log.info("UPDATING AN ORDER TO THE DB");
    }
    private void delete(BookOrder bookOrder, String storeId){
        log.info("DELETING THE ORDER FROM THE DB");
    }
}
