package com.projeto.modelo.spring.service;

import com.projeto.modelo.spring.entity.Arquivo;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ConsumerService {

    @Autowired
    ArquivoService arquivoService;

    /*@RabbitListener(ackMode = "MANUAL", queues="${amq.rabbitmq.queue}" )
    public void getDados(Dados dados, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            System.out.println("Lendo Fila Código 1: " + dados.getCodigo() + " Nome: " + dados.getNome());
            if (dados.getCodigo() == 1) {
                System.out.println("Removendo 1");
                channel.basicAck(tag, false);
            }
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            channel.basicReject (tag,false);
        }
    }*/

    @RabbitListener(ackMode = "MANUAL", queues="${amq.rabbitmq.queue}" )
    public void persistirArquivo(List<Arquivo> listArquivo, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            for (Arquivo item: listArquivo) {
                System.out.println("Lendo Fila Código: " + item.getId() + " Nome: " + item.getNome());
                arquivoService.insert(item);
            }
            channel.basicAck(tag, false);

        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            channel.basicReject (tag,false);
        }
    }
}
