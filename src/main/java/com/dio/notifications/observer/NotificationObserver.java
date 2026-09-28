package com.dio.notifications.observer;

import com.dio.notifications.model.NotificationMessage;

/**
 * PADRÃO DE PROJETO: Observer
 * <p>
 * Permite que múltiplos interessados (histórico, métricas, auditoria, etc.)
 * sejam notificados automaticamente sempre que uma notificação é enviada,
 * sem que o código de envio precise conhecer os detalhes de cada um deles.
 */
public interface NotificationObserver {

    void onNotificationSent(NotificationMessage message, String channelName, boolean success);
}
