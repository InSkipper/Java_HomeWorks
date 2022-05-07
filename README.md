# Fifth Homework

## Задание

1) Создать три EventListener’a (обычный, Async и Transactional)
2) Для каждого из них порождать событие
    - Для TransactionalEventListener порождать два события так, чтобы одно обрабатывалось, а другое нет
3) Логировать моменты порождения и обработки событий

### Создание EventListener

EventListener представляет собой бин, у которого есть метод, помеченный аннотацией **@EventListener**. Этим методом
_listener_ обрабатывает поступающий _event_.

**Обычный Event Listener** находится в
классе [CommonEventListener](https://github.com/InSkipper/Java_HomeWorks/blob/FifthHomework/src/main/java/com/example/fifthhomework/listeners/CommonEventListener.java)
. Он
слушает [MyEvent](https://github.com/InSkipper/Java_HomeWorks/blob/FifthHomework/src/main/java/com/example/fifthhomework/events/MyEvent.java)
.

```java

@Service
public class CommonEventListener {
    @EventListener
    public void listenEvent(MyEvent event) {
        System.out.println("Common event listener: " + event.getMessage()
                + " In thread: " + Thread.currentThread().getName());
    }
}
```

**Async Event Listener** находится в
классе [AsyncEventListener](https://github.com/InSkipper/Java_HomeWorks/blob/FifthHomework/src/main/java/com/example/fifthhomework/listeners/AsyncEventListener.java)
. От обычного EventListener'а Async отличается наличием еще одной анотации: **@Async**.

```java

@Service
public class AsyncEventListener {
    @Async()
    @EventListener
    public void listenEvent(MyEvent event) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Async event listener: " + event.getMessage()
                + " In thread: " + Thread.currentThread().getName());
    }
}
```

Но просто "из коробки" аннотация **@Async** не работает. Сначала нужно создать конфигурация для асинхронный вычислений:
[AsyncConfig](https://github.com/InSkipper/Java_HomeWorks/blob/FifthHomework/src/main/java/com/example/fifthhomework/configs/AsyncConfig.java)
.

```java

@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean("threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
}
```

**AsyncEventListener** для демонстрации ждет секунду и только потом выводит лог, в котором также указывается имя потока.

**Transactional Event Listener** находится в
классе [TransactEventListener](https://github.com/InSkipper/Java_HomeWorks/blob/FifthHomework/src/main/java/com/example/fifthhomework/listeners/TransactEventListener.java)
. Он обрабатывает два
события: [MyEvent](https://github.com/InSkipper/Java_HomeWorks/blob/FifthHomework/src/main/java/com/example/fifthhomework/events/MyEvent.java)
и [MyRollbackEvent](https://github.com/InSkipper/Java_HomeWorks/blob/FifthHomework/src/main/java/com/example/fifthhomework/events/MyRollbackEvent.java)
. Первое - при нормальной работе, второе - при возникновении ошибки во время транзакции. За это отвечает параметр
**phase** в аннотации **@TransactionalEventListener**.

```java

@Service
public class TransactEventListener {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void listenEvent(MyEvent event) {
        System.out.println("First Transactional event listener: " + event.getMessage()
                + " In thread: " + Thread.currentThread().getName());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void listenAnotherEvent(MyRollbackEvent event) {
        System.out.println("Second Transactional event listener: " + event.getMessage()
                + " In thread: " + Thread.currentThread().getName());
    }
}
```

Также для работы @TransactionalEventListener необходимо, чтобы событие порождалось в транзакии. Для этого на
[Event Publisher](https://github.com/InSkipper/Java_HomeWorks/blob/FifthHomework/src/main/java/com/example/fifthhomework/eventpublishers/EventPublisher.java)
нужно повесить аннотацию **@Transactional**.

### Вывод программы
```text
___Publishing MyEvent___
Common event listener: Event message! In thread: main
First Transactional event listener: Event message! In thread: main
___Publishing MyRollbackEvent___
Second Transactional event listener: Rollback event message! In thread: main
Async event listener: Event message! In thread: threadPoolTaskExecutor-1
```