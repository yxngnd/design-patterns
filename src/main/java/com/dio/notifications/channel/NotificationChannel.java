package com.dio.notifications.channel;

import com.dio.notifications.model.NotificationMessage;

/**
 * PADRÃO DE PROJETO: Strategy
 * <p>
 * Define a interface comum para os diferentes algoritmos de envio de
 * notificação (email, SMS, push). O cliente trabalha apenas com esta
 * abstração, podendo trocar a estratégia de envio em tempo de execução
 * sem alterar o código que a utiliza.
 */
public interface NotificationChannel {

    boolean send(NotificationMessage message);

    String getChannelName();
}
