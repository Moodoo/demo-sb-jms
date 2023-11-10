package com.example.demojmstransactions.jms;


import com.example.demojmstransactions.pojo.BookOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WarehouseReceiverService {
@Autowired
    WarehouseProcessingService warehouseProcessingService;

   @JmsListener(destination = "book.order.queue")
   //transaction is taken by default from DefaultJmsListenerContainerFactory
    public void receive(BookOrder bookOrder){
        log.info("Message received!");
        log.info("Message is == " + bookOrder);
        if(bookOrder.getBook().getTitle().startsWith("L"))
        {
            throw new RuntimeException("OrderId="+bookOrder.getBookOrderId()+
                   " begins with L and books are not allowed!");
        }
        warehouseProcessingService.processOrder(bookOrder);

    }
}
