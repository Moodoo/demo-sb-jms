package com.example.demojmsjson.jms;


import com.example.demojmsjson.pojo.BookOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookstoreOrderService {
    @Autowired
    private JmsTemplate jmsTemplate;
    private static final String BOOK_QUEUE = "book.order.queue";

    public void send(BookOrder bookOrder) {
        jmsTemplate.convertAndSend(BOOK_QUEUE, bookOrder);
    }

}

