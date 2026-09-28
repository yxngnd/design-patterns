package com.dio.notifications.observer;

import com.dio.notifications.model.NotificationMessage;

public class MetricsObserver implements NotificationObserver {

    private int sentCount = 0;
    private int failedCount = 0;

    @Override
    public void onNotificationSent(NotificationMessage message, String channelName, boolean success) {
        if (success) {
            sentCount++;
        } else {
            failedCount++;
        }
    }

    public int getSentCount() {
        return sentCount;
    }

    public int getFailedCount() {
        return failedCount;
    }

    @Override
    public String toString() {
        return "Métricas{enviadas=" + sentCount + ", falhas=" + failedCount + '}';
    }
}
