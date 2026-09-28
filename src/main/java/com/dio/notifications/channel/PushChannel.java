package com.dio.notifications.channel;

import com.dio.notifications.model.NotificationMessage;

public class PushChannel implements NotificationChannel {

    @Override
    public boolean send(NotificationMessage message) {
        System.out.println("[PUSH] Dispositivo de: " + message.getRecipient()
                + " | Notificação: " + message.getSubject());
        return true;
    }

    @Override
    public String getChannelName() {
        return "Push";
    }
}
