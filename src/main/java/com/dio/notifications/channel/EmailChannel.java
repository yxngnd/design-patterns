package com.dio.notifications.channel;

import com.dio.notifications.model.NotificationMessage;

public class EmailChannel implements NotificationChannel {

    @Override
    public boolean send(NotificationMessage message) {
        System.out.println("[EMAIL] Para: " + message.getRecipient()
                + " | Assunto: " + message.getSubject()
                + " | Corpo: " + message.getBody());
        return true;
    }

    @Override
    public String getChannelName() {
        return "Email";
    }
}
