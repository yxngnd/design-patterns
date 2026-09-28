package com.dio.notifications;

import com.dio.notifications.chain.ChannelHandler;
import com.dio.notifications.channel.NotificationChannel;
import com.dio.notifications.channel.UnstableChannel;
import com.dio.notifications.decorator.LoggingChannelDecorator;
import com.dio.notifications.decorator.RetryChannelDecorator;
import com.dio.notifications.factory.NotificationChannelFactory;
import com.dio.notifications.facade.NotificationFacade;
import com.dio.notifications.model.NotificationMessage;
import com.dio.notifications.model.NotificationPriority;
import com.dio.notifications.model.NotificationType;
import com.dio.notifications.singleton.NotificationConfig;
import com.dio.notifications.template.StandardNotificationProcessor;
import com.dio.notifications.template.UrgentNotificationProcessor;

public class Main {

    public static void main(String[] args) {
        secao("1. BUILDER - Construindo mensagens de forma fluente");
        NotificationMessage boasVindas = NotificationMessage.builder()
                .recipient("ana@exemplo.com")
                .subject("Bem-vinda!")
                .body("Sua conta foi criada com sucesso.")
                .priority(NotificationPriority.NORMAL)
                .build();
        System.out.println(boasVindas);

        secao("2. FACTORY METHOD + STRATEGY - Criando e usando canais");
        NotificationChannel email = NotificationChannelFactory.createChannel(NotificationType.EMAIL);
        NotificationChannel sms = NotificationChannelFactory.createChannel(NotificationType.SMS);
        NotificationChannel push = NotificationChannelFactory.createChannel(NotificationType.PUSH);
        email.send(boasVindas);
        sms.send(NotificationMessage.builder()
                .recipient("+55 75 99999-0000")
                .body("Seu código de verificação é 4821.")
                .build());
        push.send(NotificationMessage.builder()
                .recipient("dispositivo-123")
                .subject("Nova mensagem")
                .body("Você recebeu uma nova mensagem.")
                .build());

        secao("3. DECORATOR - Logging + Retry em um canal instável");
        NotificationChannel canalInstavel = new UnstableChannel("EmailInstavel", 2);
        NotificationChannel comLog = new LoggingChannelDecorator(canalInstavel);
        NotificationChannel comLogERetry = new RetryChannelDecorator(comLog, 3);
        comLogERetry.send(NotificationMessage.builder()
                .recipient("carlos@exemplo.com")
                .subject("Fatura disponível")
                .body("Sua fatura de setembro já está disponível.")
                .build());

        secao("4. CHAIN OF RESPONSIBILITY - Fallback entre canais");
        ChannelHandler handlerEmail = new ChannelHandler(new UnstableChannel("Email", 1));
        ChannelHandler handlerSms = new ChannelHandler(new UnstableChannel("SMS", 1));
        ChannelHandler handlerPush = new ChannelHandler(new UnstableChannel("Push", 0));
        handlerEmail.setNext(handlerSms).setNext(handlerPush);
        handlerEmail.handle(NotificationMessage.builder()
                .recipient("joao@exemplo.com")
                .subject("Alerta de segurança")
                .body("Detectamos um novo login na sua conta.")
                .priority(NotificationPriority.HIGH)
                .build());

        secao("5. TEMPLATE METHOD - Processamento padrão vs urgente");
        NotificationMessage mensagemNormal = NotificationMessage.builder()
                .recipient("maria@exemplo.com")
                .subject("Newsletter")
                .body("Confira as novidades deste mês.")
                .build();
        new StandardNotificationProcessor(new UnstableChannel("EmailPadrao", 0)).process(mensagemNormal);

        NotificationMessage mensagemUrgente = NotificationMessage.builder()
                .recipient("suporte@exemplo.com")
                .subject("Servidor fora do ar")
                .body("O servidor principal está indisponível!")
                .priority(NotificationPriority.URGENT)
                .build();
        new UrgentNotificationProcessor(new UnstableChannel("SmsUrgente", 0)).process(mensagemUrgente);

        secao("6. SINGLETON - Configuração global compartilhada");
        NotificationConfig config1 = NotificationConfig.getInstance();
        NotificationConfig config2 = NotificationConfig.getInstance();
        config1.setMaxRetryAttempts(5);
        System.out.println("config1 == config2 ? " + (config1 == config2));
        System.out.println("Tentativas máximas configuradas: " + config2.getMaxRetryAttempts());
        System.out.println("Remetente padrão: " + config2.getDefaultSenderName());

        secao("7. FACADE + OBSERVER - Envio simplificado com histórico e métricas");
        NotificationFacade facade = new NotificationFacade();
        facade.sendWithFallback(
                NotificationMessage.builder()
                        .recipient("cliente@exemplo.com")
                        .subject("Pedido confirmado")
                        .body("Seu pedido #4820 foi confirmado.")
                        .build(),
                NotificationType.EMAIL, NotificationType.SMS, NotificationType.PUSH
        );
        facade.sendWithFallback(
                NotificationMessage.builder()
                        .recipient("outro-cliente@exemplo.com")
                        .subject("Pagamento aprovado")
                        .body("Seu pagamento foi aprovado com sucesso.")
                        .build(),
                NotificationType.EMAIL
        );

        System.out.println("\nHistórico de envios:");
        facade.getHistoryObserver().getHistory().forEach(entry -> System.out.println("  " + entry));
        System.out.println("Métricas: " + facade.getMetricsObserver());
    }

    private static void secao(String titulo) {
        System.out.println("\n=== " + titulo + " ===");
    }
}
