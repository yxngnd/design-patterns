package com.dio.notifications.channel;

import com.dio.notifications.model.NotificationMessage;

public class SmsChannel implements NotificationChannel {

    @Override
    public boolean send(NotificationMessage message) {
        System.out.println("[SMS] Para: " + message.getRecipient()
                + " | Mensagem: " + message.getBody());
        return true;
    }

    @Override
    public String getChannelName() {
        return "SMS";
    }
}
