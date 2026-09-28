package com.dio.notifications.observer;

import com.dio.notifications.model.NotificationMessage;

import java.util.ArrayList;
import java.util.List;

public class NotificationPublisher {

    private final List<NotificationObserver> observers = new ArrayList<>();

    public void subscribe(NotificationObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(NotificationObserver observer) {
        observers.remove(observer);
    }

    public void publish(NotificationMessage message, String channelName, boolean success) {
        for (NotificationObserver observer : observers) {
            observer.onNotificationSent(message, channelName, success);
        }
    }
}
