package com.example.demojmstransactions.jms;


import com.example.demojmstransactions.pojo.BookOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreOrderService {
    @Autowired
    private JmsTemplate jmsTemplate;
    private static final String BOOK_QUEUE = "book.order.queue";

    @Transactional
    public void send(BookOrder bookOrder) {
        jmsTemplate.convertAndSend(BOOK_QUEUE, bookOrder);
    }

}

