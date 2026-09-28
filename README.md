# Sistema de Notificações — Design Patterns em Java Puro

Projeto desenvolvido para o desafio final de Padrões de Projeto. Simula um sistema de envio de notificações (Email, SMS, Push) aplicando **9 padrões de projeto** de forma integrada, em Java puro (sem frameworks).

## Como rodar

Com Maven:
```bash
mvn compile exec:java -Dexec.mainClass="com.dio.notifications.Main"
```

Ou compilando manualmente:
```bash
find src -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out com.dio.notifications.Main
```

## Padrões aplicados e onde encontrá-los

| Padrão | Categoria | Onde está | Por que foi usado |
|---|---|---|---|
| **Builder** | Criacional | `model/NotificationMessage.java` | Construir mensagens com campos opcionais/obrigatórios de forma legível, sem construtor telescópico. |
| **Factory Method** | Criacional | `factory/NotificationChannelFactory.java` | Centralizar a criação dos canais (Email/SMS/Push) sem o cliente conhecer as classes concretas. |
| **Singleton** | Criacional | `singleton/NotificationConfig.java` | Garantir uma única instância de configuração global (tentativas máximas, remetente padrão). |
| **Strategy** | Comportamental | `channel/NotificationChannel.java` + implementações | Cada canal é uma estratégia de envio intercambiável em tempo de execução. |
| **Decorator** | Estrutural | `decorator/LoggingChannelDecorator.java`, `RetryChannelDecorator.java` | Adicionar log e retry a qualquer canal sem alterar sua implementação nem explodir em subclasses. |
| **Chain of Responsibility** | Comportamental | `chain/ChannelHandler.java` | Tentar Email → SMS → Push automaticamente até que o envio tenha sucesso. |
| **Observer** | Comportamental | `observer/NotificationPublisher.java` + observers | Notificar histórico e métricas sempre que uma mensagem é enviada, sem acoplamento direto. |
| **Template Method** | Comportamental | `template/NotificationProcessor.java` + subclasses | Definir o fluxo fixo (validar → enriquecer → entregar → registrar) e deixar que envios padrão/urgentes variem apenas os passos específicos. |
| **Facade** | Estrutural | `facade/NotificationFacade.java` | Oferecer um único ponto de entrada simples, escondendo a montagem de fábrica + decorators + chain + observers. |

## Estrutura do projeto

```
src/main/java/com/dio/notifications/
├── Main.java                      # Demonstração de todos os padrões
├── model/                         # NotificationMessage (Builder), enums
├── channel/                       # Strategy (canais de envio)
├── factory/                       # Factory Method
├── decorator/                     # Decorator (log, retry)
├── chain/                         # Chain of Responsibility
├── observer/                      # Observer (histórico, métricas)
├── template/                      # Template Method
├── singleton/                     # Singleton (configuração)
└── facade/                        # Facade
```

## Possíveis evoluções

- Persistir o histórico de notificações em banco de dados.
- Adicionar um padrão **Command** para enfileirar e desfazer envios.
- Expor a `NotificationFacade` via uma API REST (ex.: migrando para Spring Boot).
