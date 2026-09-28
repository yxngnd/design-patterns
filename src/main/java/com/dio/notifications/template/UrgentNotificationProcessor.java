package com.dio.notifications.template;

import com.dio.notifications.channel.NotificationChannel;
import com.dio.notifications.model.NotificationMessage;

public class UrgentNotificationProcessor extends NotificationProcessor {

    private final NotificationChannel channel;

    public UrgentNotificationProcessor(NotificationChannel channel) {
        this.channel = channel;
    }

    @Override
    protected void enrich(NotificationMessage message) {
        System.out.println("[TEMPLATE] Notificação URGENTE! Priorizando entrega imediata.");
    }

    @Override
    protected boolean deliver(NotificationMessage message) {
        System.out.println("[TEMPLATE] Enviando com máxima prioridade via " + channel.getChannelName());
        return channel.send(message);
    }
}
