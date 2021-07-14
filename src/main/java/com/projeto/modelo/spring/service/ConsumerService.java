package com.projeto.modelo.spring.service;

import com.projeto.modelo.spring.entity.Arquivo;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConsumerService {

    @Autowired
    ArquivoService arquivoService;

    @RabbitListener(ackMode = "MANUAL", queues="${amq.rabbitmq.queue}" )
    public void persistirArquivo(Arquivo arquivo, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {

            System.out.println("Lendo Fila Código: " + arquivo.getId() + " Nome: " + arquivo.getNome());
            arquivoService.insert(arquivo);
            channel.basicAck(tag, false);

        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            channel.basicReject (tag,false);
        }
    }
}
