package com.dio.notifications.template;

import com.dio.notifications.model.NotificationMessage;

/**
 * PADRÃO DE PROJETO: Template Method
 * <p>
 * Define o esqueleto do algoritmo de processamento de uma notificação
 * (validar -> enriquecer -> entregar -> registrar), deixando que as
 * subclasses personalizem apenas os passos que variam entre um envio
 * padrão e um envio urgente.
 */
public abstract class NotificationProcessor {

    public final void process(NotificationMessage message) {
        validate(message);
        enrich(message);
        boolean delivered = deliver(message);
        logResult(message, delivered);
    }

    protected void validate(NotificationMessage message) {
        if (message.getRecipient() == null || message.getRecipient().isBlank()) {
            throw new IllegalArgumentException("Mensagem sem destinatário válido.");
        }
    }

    protected abstract void enrich(NotificationMessage message);

    protected abstract boolean deliver(NotificationMessage message);

    protected void logResult(NotificationMessage message, boolean delivered) {
        System.out.println("[TEMPLATE] Processamento de " + message.getRecipient()
                + " finalizado: " + (delivered ? "entregue" : "não entregue"));
    }
}
