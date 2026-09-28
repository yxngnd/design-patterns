package com.dio.notifications.singleton;

/**
 * PADRÃO DE PROJETO: Singleton
 * <p>
 * Garante que exista uma única instância de configuração compartilhada por
 * toda a aplicação (ex.: número máximo de tentativas de reenvio, nome do
 * remetente padrão), evitando estados de configuração divergentes.
 */
public final class NotificationConfig {

    private static NotificationConfig instance;

    private int maxRetryAttempts = 3;
    private String defaultSenderName = "DIO Notificações";

    private NotificationConfig() {
    }

    public static synchronized NotificationConfig getInstance() {
        if (instance == null) {
            instance = new NotificationConfig();
        }
        return instance;
    }

    public int getMaxRetryAttempts() {
        return maxRetryAttempts;
    }

    public void setMaxRetryAttempts(int maxRetryAttempts) {
        this.maxRetryAttempts = maxRetryAttempts;
    }

    public String getDefaultSenderName() {
        return defaultSenderName;
    }

    public void setDefaultSenderName(String defaultSenderName) {
        this.defaultSenderName = defaultSenderName;
    }
}
