package com.dio.notifications.decorator;

import com.dio.notifications.channel.NotificationChannel;
import com.dio.notifications.model.NotificationMessage;

public class LoggingChannelDecorator extends NotificationChannelDecorator {

    public LoggingChannelDecorator(NotificationChannel wrapped) {
        super(wrapped);
    }

    @Override
    public boolean send(NotificationMessage message) {
        System.out.println("[LOG] Iniciando envio via " + getChannelName()
                + " para " + message.getRecipient());
        boolean result = wrapped.send(message);
        System.out.println("[LOG] Resultado via " + getChannelName() + ": "
                + (result ? "sucesso" : "falha"));
        return result;
    }
}
