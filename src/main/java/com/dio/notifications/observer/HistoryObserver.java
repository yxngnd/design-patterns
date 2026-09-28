package com.dio.notifications.observer;

import com.dio.notifications.model.NotificationMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryObserver implements NotificationObserver {

    private final List<String> history = new ArrayList<>();

    @Override
    public void onNotificationSent(NotificationMessage message, String channelName, boolean success) {
        String entry = String.format("[%s] Para: %s | %s",
                channelName, message.getRecipient(), success ? "ENVIADO" : "FALHOU");
        history.add(entry);
    }

    public List<String> getHistory() {
        return Collections.unmodifiableList(history);
    }
}
