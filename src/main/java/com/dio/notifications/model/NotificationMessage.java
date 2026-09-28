package com.dio.notifications.model;

/**
 * Representa uma mensagem de notificação imutável.
 * <p>
 * PADRÃO DE PROJETO: Builder
 * <p>
 * A construção de uma NotificationMessage pode envolver vários campos opcionais
 * (assunto, prioridade) e obrigatórios (destinatário, corpo). O Builder evita
 * um construtor telescópico com muitos parâmetros e deixa a criação do objeto
 * legível e fluente.
 */
public class NotificationMessage {

    private final String recipient;
    private final String subject;
    private final String body;
    private final NotificationPriority priority;

    private NotificationMessage(Builder builder) {
        this.recipient = builder.recipient;
        this.subject = builder.subject;
        this.body = builder.body;
        this.priority = builder.priority;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public NotificationPriority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "NotificationMessage{" +
                "recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", priority=" + priority +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String recipient;
        private String subject = "";
        private String body;
        private NotificationPriority priority = NotificationPriority.NORMAL;

        private Builder() {
        }

        public Builder recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder priority(NotificationPriority priority) {
            this.priority = priority;
            return this;
        }

        public NotificationMessage build() {
            if (recipient == null || recipient.isBlank()) {
                throw new IllegalStateException("O destinatário é obrigatório.");
            }
            if (body == null || body.isBlank()) {
                throw new IllegalStateException("O corpo da mensagem é obrigatório.");
            }
            return new NotificationMessage(this);
        }
    }
}
