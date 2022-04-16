# Fourth Homework

## Задание

1) Создать 3 профиля - dev, test, prod, каждый из которых будет включать в себя:
    - конфиг с листом из нескольких значений
    - название приложения
    - конфиг с переменной окружения, по дефолту значение - default
2) Создать 3 бина:
    - один создается, только если профиль test,
    - другой, если существует первый бин,
    - третий, если в конфиге с не “default” (тут в идеале со скриншотом)

### Создание профилей

Активный профиль и переменная окружения указываются в
[основном конфиге](https://github.com/InSkipper/Java_HomeWorks/blob/FourthHomework/src/main/resources/application.properties)
.

Списки со значениями в конфиге создаются следующим образом:

```properties
my.list[0]=first_dev_list_value
my.list[1]=second_dev_list_value
```

Определение названия приложения и использование переменной окружения происходят в конфигах соответственно названных
"aplication-{profile_name}"(после двоеточия - значение по умолчанию)

```properties
spring.application.name=devApplication
my.env-variable=${MY_VALUE:default}
```

### Создание бинов

[FirstBean](https://github.com/InSkipper/Java_HomeWorks/blob/FourthHomework/src/main/java/com/example/fourthhomework/beans/FirstBean.java)
создается только при профиле **test**. Для этого используется анотация **@Profile** с указание имени профиля.

```java

@Service
@Profile({"test"})
public class FirstBean {
    public FirstBean() {
        System.out.println("First bean: 'Profile set to test!'");
    }
}
```

[SecondBean](https://github.com/InSkipper/Java_HomeWorks/blob/FourthHomework/src/main/java/com/example/fourthhomework/beans/SecondBean.java)
создается только при наличии первого бина. Для этого используется анотация **@ConditionalOnBean** с указанием класса
бина.

```java

@Service
@ConditionalOnBean(FirstBean.class)
public class SecondBean {
    public SecondBean() {
        System.out.println("Second bean: 'First bean exists!'");
    }
}
```

[ThirdBean](https://github.com/InSkipper/Java_HomeWorks/blob/FourthHomework/src/main/java/com/example/fourthhomework/beans/ThirdBean.java)
создается, если переменная окружения имеет значение не _default_. Для этого используется абстрактный класс **
NoneNestedConditions**, с помощью которого можно указать свойства, при которых бин не будет создаваться.

```java
public class NotWithDefaultCondition extends NoneNestedConditions {
    public NotWithDefaultCondition() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @ConditionalOnProperty(value = "my.env-variable", havingValue = "default")
    static class Condition {
    }
}
```

Используем новое условие **NotWithDefaultCondition** с помощью анотации **@Conditional**.

```java

@Service
@Conditional(NotWithDefaultCondition.class)
public class ThirdBean {
    public ThirdBean() {
        System.out.println("Third Bean: 'Env variable set to non-default value!'");
    }
}
```

Вывод программы при профиле **test** и определенном значении переменной окружения. Последние две строчки - список
значений в конфиге и значение переменной окружения соответственно:

```
2022-04-16 20:31:30.838  INFO 4804 --- [           main] c.e.f.FourthHomeworkApplication          : Starting FourthHomeworkApplication using Java 11.0.14.1 on DESKTOP-EJ6F018 with PID 4804 (D:\JavaHomeWork_rep\Java_HomeWorks\target\classes started by Dan in D:\JavaHomeWork_rep\Java_HomeWorks)
2022-04-16 20:31:30.841  INFO 4804 --- [           main] c.e.f.FourthHomeworkApplication          : The following 1 profile is active: "test"
First bean: 'Profile set to test!'
Second bean: 'First bean exists!'
Third Bean: 'Env variable set to non-default value!
2022-04-16 20:31:31.372  INFO 4804 --- [           main] c.e.f.FourthHomeworkApplication          : Started FourthHomeworkApplication in 1.003 seconds (JVM running for 2.245)
[first_test_list_value, second_test_list_value]
Environment_variable_value
```

Вывод программы при профиле **prod** и не определенным значением переменной окружения:

```
2022-04-16 20:37:23.600  INFO 27604 --- [           main] c.e.f.FourthHomeworkApplication          : Starting FourthHomeworkApplication using Java 11.0.14.1 on DESKTOP-EJ6F018 with PID 27604 (D:\JavaHomeWork_rep\Java_HomeWorks\target\classes started by Dan in D:\JavaHomeWork_rep\Java_HomeWorks)
2022-04-16 20:37:23.604  INFO 27604 --- [           main] c.e.f.FourthHomeworkApplication          : The following 1 profile is active: "prod"
2022-04-16 20:37:24.128  INFO 27604 --- [           main] c.e.f.FourthHomeworkApplication          : Started FourthHomeworkApplication in 0.975 seconds (JVM running for 1.871)
[first_prod_list_value, second_prod_list_value, third_prod_list_value]
default
```