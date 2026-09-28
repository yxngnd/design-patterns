package com.dio.notifications.chain;

import com.dio.notifications.channel.NotificationChannel;
import com.dio.notifications.model.NotificationMessage;

/**
 * PADRÃO DE PROJETO: Chain of Responsibility
 * <p>
 * Monta uma cadeia de canais de notificação. Se o canal atual falhar ao
 * enviar a mensagem, a responsabilidade é repassada automaticamente para o
 * próximo elo da cadeia (ex.: tenta Email, se falhar tenta SMS, se falhar
 * tenta Push).
 */
public class ChannelHandler {

    private final NotificationChannel channel;
    private ChannelHandler next;

    public ChannelHandler(NotificationChannel channel) {
        this.channel = channel;
    }

    public ChannelHandler setNext(ChannelHandler next) {
        this.next = next;
        return next;
    }

    public boolean handle(NotificationMessage message) {
        boolean sent = channel.send(message);
        if (sent) {
            return true;
        }
        System.out.println("[CHAIN] Falha via " + channel.getChannelName() + ", repassando para o próximo canal...");
        if (next != null) {
            return next.handle(message);
        }
        System.out.println("[CHAIN] Todos os canais da cadeia falharam.");
        return false;
    }
}
