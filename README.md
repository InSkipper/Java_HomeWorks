# Ninth Homeworks

## Задание

Реализовать с помощью АОП приложение, которое будет считать количество обращений к АПИ методам и если количество
обращений достигнет максимального (максимальное количество обращений задается в конфигурации), то не допускать
выполнения АПИ метода

### Аннотация

Сначала создадим свою [аннотацию](https://github.com/InSkipper/Java_HomeWorks/blob/NinethHomework/src/main/java/com/example/defaultproject/annotation/WithApiMaxCount.java). С её помощью мы отметим метод АПИ, для которого установим ограничение в количесве
вызовов.

```java

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WithApiMaxCount {
}
```

**@Target** определяет контекст, в котором применим тип аннотации. В данном случае укажем, что аннотация применяется на
метод. **@Retention** определяет в каком месте жизненного цикла программы применяется аннотация, например в рантайме.

### Контроллер

Теперь создадим [контроллер](https://github.com/InSkipper/Java_HomeWorks/blob/NinethHomework/src/main/java/com/example/defaultproject/controller/MyController.java) с АПИ методом. И отметим метод нашей [аннотацией](https://github.com/InSkipper/Java_HomeWorks/blob/NinethHomework/src/main/java/com/example/defaultproject/annotation/WithApiMaxCount.java).

```java

@RestController
public class MyController {
    @GetMapping("/api")
    @WithApiMaxCount
    public String apiMethod() {
        return "API response";
    }
}
```

### Аспект

В АОП существуют такие понятия:

- **Join point** — точка присоединения к коду, в которой планируется введение функциональности.
- **Point cut** — срез, запрос точек присоединения.
- **Advice** — набор инструкций выполняемых на точках среза.
- **Aspect** же — это модуль в котором собраны описания Pointcut и Advice.

[Создадим](https://github.com/InSkipper/Java_HomeWorks/blob/NinethHomework/src/main/java/com/example/defaultproject/aspect/WithApiMaxCountAspect.java) срез точек присоединения. В нашем случае это просто пустой метод, помеченный аннотацией
**@Pointcut**. В аргументе мы должны указать место среза, в нашем случае это аннотация **WithApiMaxCount**. Кстати,
местом среза могут быть одна или несколько точек. Их можно объеденять с помощью **&&, ||, !**.

```java
@Pointcut("@annotation(com.example.defaultproject.annotation.WithApiMaxCount)")
public void withApiMaxCountMethods(){}
```

Далее [создадим](https://github.com/InSkipper/Java_HomeWorks/blob/NinethHomework/src/main/java/com/example/defaultproject/aspect/WithApiMaxCountAspect.java) 
другой метод, который будет помечен advice аннотацией **@Before**. Эта аннотация ссылается на метод,
который мы создали ранее. Это значит что **checkMaxCount()** выполнится перед вызовом метода, подходящего под
условия среза.

В аргументе **checkMaxCount()** находится **Join point** - непосредственно метод, выполнение которого мы прервали.
Остается лишь реализовать бизнес логику проверки количества вызовов метода.     


```java
@Before("withApiMaxCountMethods()")
public void checkMaxCount(JoinPoint point) {
    var methodName = String.format("%s.%s",
            point.getStaticPart().getSignature().getDeclaringType().getName(),
            point.getSignature().getName());
    if (maxCount > currentCount) {
        currentCount++;
        log.info("Method {} was called {} times out of {}", methodName, currentCount, maxCount);
    } else {
        log.warn("Method {} has reached the max number of calls : {}", methodName, maxCount);
        throw new RuntimeException("MAX number of calls");
    }
}
```

Максимально допустимое количество вызовов мы получаем из [конфига](https://github.com/InSkipper/Java_HomeWorks/blob/NinethHomework/src/main/resources/application.properties).

```properties
api.max-count=2
```

Используем это значение на поле с помощью аннотации @Value, в которую передаем имя переменной из конфига. [Класс аспекта](https://github.com/InSkipper/Java_HomeWorks/blob/NinethHomework/src/main/java/com/example/defaultproject/aspect/WithApiMaxCountAspect.java)
обозначается аннотацией **@Aspect**. Также накинем на этот класс аннотация ламбока **@Slf4j**, чтобы иметь возможность
оспользовать логгер.

```java

@Component
@Aspect
@Slf4j
public class WithApiMaxCountAspect {
    @Value("${api.max-count}")
    private int maxCount;
    private int currentCount;

    // @Pointcut и @After
}
```

## Вывод программы

Максимальное количество вызовов в конфиге указано как 2. Поэтому наш АПИ метод выполнится 2 раза, а на третий выкинет
ошибку _(вывод сокращен)_.

```text
2022-05-19 15:11:49.657  INFO 13544 --- [nio-8080-exec-1] c.e.d.aspect.WithApiMaxCountAspect       : Method com.example.defaultproject.controller.MyController.apiMethod was called 1 times out of 2
2022-05-19 15:11:50.545  INFO 13544 --- [nio-8080-exec-3] c.e.d.aspect.WithApiMaxCountAspect       : Method com.example.defaultproject.controller.MyController.apiMethod was called 2 times out of 2
2022-05-19 15:11:51.364  WARN 13544 --- [nio-8080-exec-4] c.e.d.aspect.WithApiMaxCountAspect       : Method com.example.defaultproject.controller.MyController.apiMethod has reached the max number of calls : 2
2022-05-19 15:11:51.376 ERROR 13544 --- [nio-8080-exec-4] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.RuntimeException: MAX number of calls] with root cause
```
