package com.example.demojmstransactions.config;


import com.example.demojmstransactions.listener.BookOrderProcessingMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Slf4j
@EnableTransactionManagement
@EnableJms
@Configuration
public class JmsConfigDeadLetterQueue implements JmsListenerConfigurer {
    @Bean
    public MessageConverter jacksonJmsMessageConverter(){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory
                ("admin","admin","tcp://localhost:61616");
        return factory;
    }


    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(jacksonJmsMessageConverter());
        factory.setTransactionManager(jmsTransactionManager());
        factory.setErrorHandler(t->{
            log.info("Handling error in listener for messages, error" + t.getMessage());
        });
        return factory;
    }

    @Bean
    public BookOrderProcessingMessageListener customListener()
    {
        BookOrderProcessingMessageListener listener=new BookOrderProcessingMessageListener();
        return listener;
    }
    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
        SimpleJmsListenerEndpoint endpoint=new SimpleJmsListenerEndpoint();
        endpoint.setMessageListener(customListener());
        endpoint.setDestination("book.order.processed.queue");
        endpoint.setId("book-order-processed-queue");
        endpoint.setConcurrency("1-1");
        endpoint.setSubscription("custom-subscription");
        registrar.registerEndpoint(endpoint);
        registrar.setContainerFactory(jmsListenerContainerFactory());
    }
    @Bean
    public PlatformTransactionManager jmsTransactionManager()
    {
        return new JmsTransactionManager(connectionFactory());
    }

@Bean
    public JmsTemplate jmsTemplate()
{
    JmsTemplate jmsTemplate=new JmsTemplate(connectionFactory());
    jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
    jmsTemplate.setDeliveryPersistent(true);
    jmsTemplate.setSessionTransacted(true);// use platform transaction manager
    return  jmsTemplate;
}

}
