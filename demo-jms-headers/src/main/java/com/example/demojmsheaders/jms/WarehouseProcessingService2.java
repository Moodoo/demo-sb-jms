package com.example.demojmsheaders.jms;


import com.example.demojmsheaders.pojo.BookOrder;
import com.example.demojmsheaders.pojo.ProcessedBookOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
//@Service
public class WarehouseProcessingService2 {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    public ProcessedBookOrder processOrder(BookOrder bookOrder, String orderState, String storeId){

        if("NEW".equalsIgnoreCase(orderState)){
            return add(bookOrder, storeId);
        } else if("UPDATE".equalsIgnoreCase(orderState)){
            return update(bookOrder, storeId);
        } else if("DELETE".equalsIgnoreCase(orderState)){
            return delete(bookOrder,storeId);
        } else{
            throw new IllegalArgumentException("WarehouseProcessingService.processOrder(...) - orderState does not match expected criteria!");
        }

    }

    private ProcessedBookOrder add(BookOrder bookOrder, String storeId){
        log.info("ADDING A NEW ORDER TO DB");
        //TODO - some type of db operation
        return new ProcessedBookOrder(
                bookOrder,
                new Date(),
                new Date()
        );
    }
    private ProcessedBookOrder update(BookOrder bookOrder, String storeId){
        log.info("UPDATING A ORDER TO DB");
        //TODO - some type of db operation
        return new ProcessedBookOrder(
                bookOrder,
                new Date(),
                new Date()
        );
    }
    private ProcessedBookOrder delete(BookOrder bookOrder, String storeId){
        log.info("DELETING ORDER FROM DB");
        //TODO - some type of db operation
        return new ProcessedBookOrder(
                bookOrder,
                new Date(),
                null
        );
    }
}
