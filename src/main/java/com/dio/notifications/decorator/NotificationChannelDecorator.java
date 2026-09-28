package com.dio.notifications.decorator;

import com.dio.notifications.channel.NotificationChannel;

/**
 * PADRÃO DE PROJETO: Decorator
 * <p>
 * Permite adicionar comportamentos extras (log, retry, etc.) a um canal de
 * notificação existente, sem alterar sua implementação e sem precisar criar
 * uma explosão de subclasses para cada combinação de comportamento.
 */
public abstract class NotificationChannelDecorator implements NotificationChannel {

    protected final NotificationChannel wrapped;

    protected NotificationChannelDecorator(NotificationChannel wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String getChannelName() {
        return wrapped.getChannelName();
    }
}
