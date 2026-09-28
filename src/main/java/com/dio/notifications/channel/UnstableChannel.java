package com.dio.notifications.channel;

import com.dio.notifications.model.NotificationMessage;

/**
 * Canal que simula falhas nas N primeiras tentativas antes de ter sucesso.
 * Usado apenas para demonstrar os padrões Decorator (Retry) e
 * Chain of Responsibility de forma didática.
 */
public class UnstableChannel implements NotificationChannel {

    private final String name;
    private final int failuresBeforeSuccess;
    private int attempts = 0;

    public UnstableChannel(String name, int failuresBeforeSuccess) {
        this.name = name;
        this.failuresBeforeSuccess = failuresBeforeSuccess;
    }

    @Override
    public boolean send(NotificationMessage message) {
        attempts++;
        if (attempts <= failuresBeforeSuccess) {
            System.out.println("[" + name + "] Falha simulada na tentativa " + attempts);
            return false;
        }
        System.out.println("[" + name + "] Mensagem entregue para " + message.getRecipient());
        return true;
    }

    @Override
    public String getChannelName() {
        return name;
    }
}
