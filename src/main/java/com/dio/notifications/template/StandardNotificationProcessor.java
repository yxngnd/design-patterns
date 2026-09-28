package com.dio.notifications.template;

import com.dio.notifications.channel.NotificationChannel;
import com.dio.notifications.model.NotificationMessage;

public class StandardNotificationProcessor extends NotificationProcessor {

    private final NotificationChannel channel;

    public StandardNotificationProcessor(NotificationChannel channel) {
        this.channel = channel;
    }

    @Override
    protected void enrich(NotificationMessage message) {
        System.out.println("[TEMPLATE] Processando notificação padrão, sem alterações no conteúdo.");
    }

    @Override
    protected boolean deliver(NotificationMessage message) {
        return channel.send(message);
    }
}
