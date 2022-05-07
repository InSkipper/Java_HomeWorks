# Java Homeworks

## Задание

1) Реализовать два контроллера. Один контроллер должен в качестве ответа на GET запрос отдавать шаблон, в котором
   выводится список всех заголовков запроса. Второй контроллер должен принимать на вход JSON вида:

```
{
   "price": 125.0,
   "info": {
   "date": "2022-01-01"
   } 
} и отдавать его обратно, при этом добавив в ответ свойство id:
{
   "price": 125.0,
   "info": {
   "id": 123,
   "date": "2022-01-01"
   }
}
```

2) Написать обработчик ошибок, который будет возвращать кастомную 502 ошибку

### Список заголовков запроса

[Первоый котроллер](https://github.com/InSkipper/Java_HomeWorks/blob/SixthHomework/src/main/java/com/example/defaultproject/controllers/FirstController.java)
будет **@RestController**. Для получения списка заголовков используется аннотация **@RequestHeader**.

```java

@RestController
public class FirstController {
    @GetMapping("/headers")
    public String readHeaders(@RequestHeader Map<String, String> headers) {
        var sb = new StringBuilder();
        headers.forEach((key, value) -> {
            sb.append(key).append("\n");
        });

        return sb.toString();
    }
}
```

![Headers](C:\Users\Dan\Desktop\руфвукы.png "Headers")

### JSON на вход

_Spring_ умеет автоматически парсить _JSON_ файл в _POJO_ класс с помощью _Jackson_. Для этого перед аргументом метода
контроллера нужно написать аннотацию **@RequestBody** и
свой [POJO класс](https://github.com/InSkipper/Java_HomeWorks/blob/SixthHomework/src/main/java/com/example/defaultproject/dto/MyDTO.java)
.

```java

@Controller
public class SecondController {
    @GetMapping("/json")
    public ResponseEntity<MyDTO> readJSON(@RequestBody MyDTO input) {
        return new ResponseEntity<>(input.setId(123), HttpStatus.OK);
    }
}
```

![json](C:\Users\Dan\Desktop\json.png)

### Обработчик ошибок

Для обработки ошибок
используется [класс](https://github.com/InSkipper/Java_HomeWorks/blob/SixthHomework/src/main/java/com/example/defaultproject/controlleradvises/DefaultAdvise.java)
с аннотацией **@ControllerAdvice**, в котором будет метод, помеченный аннотацией
**@ExceptionHandler**. В параметр последней анннотации передается класс ошибки, который мы хотим обрабатывать.

```java

@ControllerAdvice
public class DefaultAdvise {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception e) {
        var response = new Response("502 Bad Gateway");
        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }
}
```

Теперь, если мы викинем определенную ошибку
в [третьем контроллере](https://github.com/InSkipper/Java_HomeWorks/blob/SixthHomework/src/main/java/com/example/defaultproject/controllers/ThirdController.java)
, то она обработается нашим обработчиком.

```java

@RestController
public class ThirdController {
    @GetMapping("/exception")
    public Response testException(@RequestParam(required = false, defaultValue = "false") boolean exception) throws Exception {
        if (exception) {
            throw new Exception();
        }
        return new Response("Ok");
    }
}
```
![exeption](C:\Users\Dan\Desktop\exeption.png)