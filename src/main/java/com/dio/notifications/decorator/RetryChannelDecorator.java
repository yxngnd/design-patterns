package com.dio.notifications.decorator;

import com.dio.notifications.channel.NotificationChannel;
import com.dio.notifications.model.NotificationMessage;

public class RetryChannelDecorator extends NotificationChannelDecorator {

    private final int maxAttempts;

    public RetryChannelDecorator(NotificationChannel wrapped, int maxAttempts) {
        super(wrapped);
        this.maxAttempts = maxAttempts;
    }

    @Override
    public boolean send(NotificationMessage message) {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            System.out.println("[RETRY] Tentativa " + attempt + "/" + maxAttempts
                    + " via " + getChannelName());
            if (wrapped.send(message)) {
                return true;
            }
        }
        System.out.println("[RETRY] Todas as " + maxAttempts + " tentativas falharam via " + getChannelName());
        return false;
    }
}
