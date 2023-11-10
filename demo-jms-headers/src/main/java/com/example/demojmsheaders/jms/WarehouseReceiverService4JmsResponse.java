package com.example.demojmsheaders.jms;


import com.example.demojmsheaders.pojo.BookOrder;
import com.example.demojmsheaders.pojo.ProcessedBookOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WarehouseReceiverService4JmsResponse {

    @Autowired
    private WarehouseProcessingService4JmsResponse warehouseProcessingService;
    @JmsListener(destination = "book.order.queue")
   //calculate destination on the fly with JmsResponse

    // to resend message with headers
    public JmsResponse<Message<ProcessedBookOrder>> receive(@Payload BookOrder bookOrder,
                                                            @Header(name = "orderState") String orderState,
                                                            @Header(name = "bookOrderId") String bookOrderId,
                                                            @Header(name = "storeId") String storeId,
                                                            MessageHeaders headers) {
        log.info("Message received!");
        log.info("Message is == " + bookOrder);
        log.info("Message property orderState = {}, bookOrderId = {}, storeId = {}", orderState, bookOrderId, storeId);
        log.info("messageHeaders = {}", headers);

        if(bookOrder.getBook().getTitle().startsWith("L")){
            throw new IllegalArgumentException("bookOrderId=" + bookOrder.getBookOrderId() + " is of a book not allowed!");
        }
      return  warehouseProcessingService.processOrder(bookOrder, orderState, storeId);

    }
}
