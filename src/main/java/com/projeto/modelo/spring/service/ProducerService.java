package com.projeto.modelo.spring.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${amq.rabbitmq.exchange}")
    private String exchange;

    @Value("${amq.rabbitmq.routingkey}")
    private String routingKey;

    // Producer - Envia os dados para a Fila
    public void enviarMensagem(Object mensagem){
        this.rabbitTemplate.convertAndSend(exchange, routingKey, mensagem);
    }
}
