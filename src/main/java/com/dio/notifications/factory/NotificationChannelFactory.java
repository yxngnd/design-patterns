package com.dio.notifications.factory;

import com.dio.notifications.channel.EmailChannel;
import com.dio.notifications.channel.NotificationChannel;
import com.dio.notifications.channel.PushChannel;
import com.dio.notifications.channel.SmsChannel;
import com.dio.notifications.model.NotificationType;

/**
 * PADRÃO DE PROJETO: Factory Method
 * <p>
 * Centraliza a lógica de criação dos canais de notificação. O cliente não
 * precisa conhecer as classes concretas (EmailChannel, SmsChannel, ...),
 * apenas solicita um canal a partir do tipo desejado.
 */
public class NotificationChannelFactory {

    private NotificationChannelFactory() {
    }

    public static NotificationChannel createChannel(NotificationType type) {
        return switch (type) {
            case EMAIL -> new EmailChannel();
            case SMS -> new SmsChannel();
            case PUSH -> new PushChannel();
        };
    }
}
