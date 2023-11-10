package com.example.demojmstransactions.rest;


import com.example.demojmstransactions.jms.BookstoreOrderService;
import com.example.demojmstransactions.jms.WarehouseProcessingService;
import com.example.demojmstransactions.pojo.Book;
import com.example.demojmstransactions.pojo.BookOrder;
import com.example.demojmstransactions.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("order")
public class SendBookOrder {
    @Autowired
    BookstoreOrderService orderService;
    @Autowired
    WarehouseProcessingService warehouseProcessingService;
    @RequestMapping("send")
    public BookOrder sendOrder()
    {
        Random rand=new Random();
        Book book =new Book(String.valueOf(rand.nextInt()),"Ltitle"+rand.nextBoolean());
         Customer customer= new Customer(String.valueOf(rand.nextInt()),"full Name");
        BookOrder bookOrder=new BookOrder(String.valueOf(rand.nextInt()),book,customer);
        orderService.send(bookOrder);
       // warehouseProcessingService.processOrder(bookOrder);
        return bookOrder;
    }

}
