# Third Homework

## Задание
Реализовать приложение (не важно какая логика, так как основой упор идет на DI), в котором:
- [используется внедрение через конструктор](https://github.com/InSkipper/Java_HomeWorks/tree/ThirdHomework#внедрение-через-конструктор)
- [используется внедрение через сеттеры](https://github.com/InSkipper/Java_HomeWorks/tree/ThirdHomework#внедрение-через-сеттеры)
- [используется внедрение через поле](https://github.com/InSkipper/Java_HomeWorks/tree/ThirdHomework#внедрение-через-поле)
- [создаются и внедряются два бина разных классов, но реализующих один интерфейс](https://github.com/InSkipper/Java_HomeWorks/tree/ThirdHomework#бины-одного-интерфейса)
- [при создании и “разрушении” бина в лог выводится сообщение](https://github.com/InSkipper/Java_HomeWorks/tree/ThirdHomework#создание-и-разрушение-бина)

  [Вывод программы](https://github.com/InSkipper/Java_HomeWorks/tree/ThirdHomework#вывод-программы)

### Внедрение через конструктор
Внедрение через конструктор происходит в классе [Flashlight](https://github.com/InSkipper/Java_HomeWorks/blob/ThirdHomework/src/main/java/com/example/thirdhomework/electronics/Flashlight.java).
Конструктор генерируется автоматически с помощью аннотации _Ламбока_ **@RequiredArgsConstructor**. 

Здесь происходит внедрение объекта, реализующего интерфейс **Battery**. В данном случае можно не использовать аннотацию **@Qualifier** для уточнения имени внедряемого бина, так как имя поля совпадает с именем бина.
```java
@Service
@RequiredArgsConstructor
public class Flashlight {
    private final Battery alkalineBattery;

    public void doWork() {
        System.out.println("A beam of light illuminated the room.");
        System.out.println("The built-in screen showed " + alkalineBattery.getChargeLevel() + "%");
        System.out.println("Battery hashcode: " + alkalineBattery.hashCode());
    }
}
```

### Внедрение через сеттеры
Рассмотрим класс [MusicPlayer](https://github.com/InSkipper/Java_HomeWorks/blob/ThirdHomework/src/main/java/com/example/thirdhomework/electronics/MusicPlayer.java). В этом классе внедрение происходит через метод **serBattery**. С этим методом используется аннотация **@Autowired**, которая позволяет _Спрингу_ подтянуть необходимые связи. 

Использование аннотации **@Qualifier** необходимо, в данном случае.

```java
@Service
public class MusicPlayer {
    private Battery battery;

    @Autowired
    public void serBattery(@Qualifier("alkalineBattery") Battery battery) {
        this.battery = battery;
    }

    public void doWork() {
        System.out.println("*Click*");
        System.out.println("You hear a quiet noise coming from player.");
        System.out.println("The built-in screen showed " + battery.getChargeLevel() + "%");
        System.out.println("Battery hashcode: " + battery.hashCode());
    }
}
```

### Внедрение через поле
Обратимся к классу [WallClock](https://github.com/InSkipper/Java_HomeWorks/blob/ThirdHomework/src/main/java/com/example/thirdhomework/electronics/WallClock.java). Внедрение происходит через поле **battery**. Также используем аннотации **@Autowired** и **@Qualifier**.
```java
@Service
public class WallClock {
    @Autowired
    @Qualifier("lithiumIonBattery")
    private Battery battery;

    public void doWork() {
        System.out.println("The clock showed: 'Current time: perfect for lunch'");
        System.out.println("The built-in screen showed " + battery.getChargeLevel() + "%");
        System.out.println("Battery hashcode: " + battery.hashCode());
    }
}
```

### Бины одного интерфейса
Если существуют несколько бинов разных классов, но реализующих один интерфейс, то _Спринг_ не в состоянии сам решить какой из них ему внедрять. Мы можем ему помочь. 

Используя аннотацию **@Qualifier**, указав в скобочка имя бина (по умолчанию - название класса), мы помогаем _Спрингу_ определить ожидаемый бин.
```java
@Autowired
@Qualifier("lithiumIonBattery")
private Battery battery;
```

Также вместо **@Qualifier**, можно просто дать полю имя внедряемого бина ```private final Battery alkalineBattery;```.

### Создание и разрушение бина
Рассмотрим на примере фрагмета класса [AlkalineBattery](https://github.com/InSkipper/Java_HomeWorks/blob/ThirdHomework/src/main/java/com/example/thirdhomework/batteries/AlkalineBattery.java).
```java
@PostConstruct
    public void postConstruct() {
        System.out.println("You took the alkaline battery out of you pocket. Code: " + hashCode());
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("You shoved the alkaline battery in your pocket. Code: " + hashCode());
    }
```

 Метод помеченный аннотацией **@PostConstruct** вызывается после инициализации бина. А метод с аннотацией **@PreDestroy**, как ни странно, перед "разрушением" бина (освобождением памяти). Но **preDestroy** метод не вызывается, если **Scope** выставлен на **Prototype**, как в случае с [AlkalineBattery](https://github.com/InSkipper/Java_HomeWorks/blob/ThirdHomework/src/main/java/com/example/thirdhomework/batteries/AlkalineBattery.java), так как _Spring_ не управляет полным жизненным циклом prototype бина.
 
### Вывод программы
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.6.4)

2022-03-17 14:25:53.854  INFO 3144 --- [           main] c.e.t.ThirdHomeworkApplication           : Starting ThirdHomeworkApplication using Java 11.0.14.1 on DESKTOP-EJ6F018 with PID 3144 (C:\Users\Dan\IdeaProjects\ThirdHomework\target\classes started by Dan in C:\Users\Dan\IdeaProjects\ThirdHomework)
2022-03-17 14:25:53.857  INFO 3144 --- [           main] c.e.t.ThirdHomeworkApplication           : No active profile set, falling back to 1 default profile: "default"
You took the alkaline battery out of you pocket. Code: 1401737458
You took the alkaline battery out of you pocket. Code: 1709700394
You took the lithium-ion battery out of you pocket. Code: 2029645118
2022-03-17 14:25:54.513  INFO 3144 --- [           main] c.e.t.ThirdHomeworkApplication           : Started ThirdHomeworkApplication in 1.108 seconds (JVM running for 1.97)
A beam of light illuminated the room.
The built-in screen showed 58%
Battery hashcode: 1401737458

*Click*
You hear a quiet noise coming from player.
The built-in screen showed 94%
Battery hashcode: 1709700394

The clock showed: 'Current time: perfect for lunch'
The built-in screen showed 18%
Battery hashcode: 2029645118

You shoved the lithium-ion battery in your pocket. Code: 2029645118

Process finished with exit code 0

```
