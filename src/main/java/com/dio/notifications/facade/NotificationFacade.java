package com.dio.notifications.facade;

import com.dio.notifications.chain.ChannelHandler;
import com.dio.notifications.channel.NotificationChannel;
import com.dio.notifications.decorator.LoggingChannelDecorator;
import com.dio.notifications.factory.NotificationChannelFactory;
import com.dio.notifications.model.NotificationMessage;
import com.dio.notifications.model.NotificationType;
import com.dio.notifications.observer.HistoryObserver;
import com.dio.notifications.observer.MetricsObserver;
import com.dio.notifications.observer.NotificationPublisher;

/**
 * PADRÃO DE PROJETO: Facade
 * <p>
 * Oferece um ponto de entrada único e simples para o cliente, escondendo a
 * complexidade de montar a fábrica de canais, os decorators, a cadeia de
 * fallback e a publicação para os observers.
 */
public class NotificationFacade {

    private final NotificationPublisher publisher = new NotificationPublisher();
    private final HistoryObserver historyObserver = new HistoryObserver();
    private final MetricsObserver metricsObserver = new MetricsObserver();

    public NotificationFacade() {
        publisher.subscribe(historyObserver);
        publisher.subscribe(metricsObserver);
    }

    public boolean sendWithFallback(NotificationMessage message, NotificationType... channelOrder) {
        ChannelHandler first = null;
        ChannelHandler last = null;

        for (NotificationType type : channelOrder) {
            NotificationChannel channel = NotificationChannelFactory.createChannel(type);
            NotificationChannel decorated = new LoggingChannelDecorator(channel);
            ChannelHandler handler = new ChannelHandler(decorated);

            if (first == null) {
                first = handler;
                last = handler;
            } else {
                last = last.setNext(handler);
            }
        }

        boolean success = first != null && first.handle(message);
        String channelsUsed = channelOrder.length > 0 ? channelOrder[0].name() : "nenhum";
        publisher.publish(message, channelsUsed + "(+fallback)", success);
        return success;
    }

    public HistoryObserver getHistoryObserver() {
        return historyObserver;
    }

    public MetricsObserver getMetricsObserver() {
        return metricsObserver;
    }
}
