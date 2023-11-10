package com.example.demojmsheaders.jms;


import com.example.demojmsheaders.pojo.BookOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

//@Service
@Slf4j
public class WarehouseReceiverService {

    @Autowired
    private WarehouseProcessingService warehouseProcessingService;
    @JmsListener(destination = "book.order.queue")
    public void receive(@Payload BookOrder bookOrder,
                        @Header(name = "orderState") String orderState,
                        @Header(name = "bookOrderId") String bookOrderId,
                        @Header(name = "storeId") String storeId,
                        MessageHeaders headers) {
        log.info("Message received!");
        log.info("Message is == " + bookOrder);
        log.info("Message property orderState = {}, bookOrderId = {}, storeId = {}", orderState, bookOrderId, storeId);
        log.info("messageHeaders = {}", headers);
        warehouseProcessingService.processOrder(bookOrder, orderState, storeId);

    }
}
