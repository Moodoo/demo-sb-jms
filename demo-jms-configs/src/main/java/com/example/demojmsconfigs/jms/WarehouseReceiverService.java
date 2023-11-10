package com.example.demojmsconfigs.jms;


import com.example.demojmsconfigs.pojo.BookOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WarehouseReceiverService {

   @JmsListener(destination = "book.order.queue")
    public void receive(BookOrder bookOrder){
        log.info("Message received!");
        log.info("Message is == " + bookOrder);
    }
}
