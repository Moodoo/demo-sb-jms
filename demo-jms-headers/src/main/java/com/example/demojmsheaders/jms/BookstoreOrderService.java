package com.example.demojmsheaders.jms;


import com.example.demojmsheaders.pojo.BookOrder;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreOrderService {
    @Autowired
    private JmsTemplate jmsTemplate;
    private static final String BOOK_QUEUE = "book.order.queue";

    @Transactional
    public void send(BookOrder bookOrder, String storeId, String orderState)
    {
        jmsTemplate.convertAndSend(BOOK_QUEUE, bookOrder, new MessagePostProcessor() {
        @Override

        public Message postProcessMessage(Message message) throws JMSException {
            message.setStringProperty("bookOrderId", bookOrder.getBookOrderId());
            message.setStringProperty("storeId", storeId);
            message.setStringProperty("orderState", orderState);
            return message;
        };
    });
    }

}

