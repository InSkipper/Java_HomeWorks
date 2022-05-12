# Seventh Homework

## Задание

1) создать контроллер, в котором:

   endpoint, который на вход будет принимать данные в виде:

```text
{
"name": "Мой список дел",
"events": ["дело1", "delo2", "delo3"]
} 
```

валидировать их и сохранять в базу

endpoint, который будет отдавать данные из бд в виде:

```text
[
{
"name": "Мой список дел",
"events": ["дело1", "delo2", "delo3"]
}, {
"name": "Мой список дел2",
"events": ["дело1", "delo2", "delo3"]
}
]
```

2) спроектировать структуру БД для этих данных. В качестве БД можно взять любую БД

### Контроллер

В контроллере три метода: для добавления вхождения, получения всех вхождений и очистки БД соответственно. Для
преобразования JSON файла в POJO-class используется аннотация **@RequestBody**. Аннотация **@Valid** указывает _Spring_'
у, что аргумент необходимо проверить на валидность.

```java

@RestController
public class MyController {
    private final MyRepository repo;

    @Autowired
    public MyController(MyRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/post")
    public String readJSON(@RequestBody @Valid ToDoList input) {
        repo.save(input);
        return String.format("Добавлен список дел №%d c именем '%s'", input.getId(), input.getName());
    }

    @GetMapping("/get")
    public Iterable<ToDoList> getDB() {
        return repo.findAll();
    }

    @DeleteMapping("/clear")
    public String clearDB() {
        var count = repo.count();
        repo.deleteAll();
        return "Было удалено вхождений: " + count;
    }
}
```

### Валидность

Валидность задается с помощью аннотаций над полями класса.

С помощью **@Length** задается минимальная и максимальная длины строки.

**@NotBlank** указывает на то, что стока не _NULL_ и имеет хотя бы один значащий символ.

**@Size** задает минимальный и максимальный размеры коллекции.

```java
public class ToDoList {
    private Long id;

    @Length(max = 50)
    @NotBlank
    private String name;

    @Size(min = 1, max = 10)
    @NotNull
    private List<@NotBlank @Length(max = 50) String> events;
}
```

### База данных

_Spring_ позволяет меньше задумываться о создании БД. Нам необходимо лишь описать сущности БД и немного "поковырять"
конфиги, а организацией БД займется _Hibernate_.

Класс сущности помечается аннотацией **@Entity**.

Каждая сущность должна иметь первичный ключ, который помечается аннотацией **@Id**.

**@GeneratedValue** позволяет генерировать значения поля автоматически.

**@Column** задает имя столбцов.

**@ElementCollection** указывает, что поле является коллекцией экземпляров втраиваемого класса.

```java

@Entity
@Getter
@Setter
@Table(name = "todolists")
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Length(max = 50)
    @NotBlank
    private String name;

    @ElementCollection
    @Column(name = "events")
    @Size(min = 1, max = 10)
    @NotNull
    private List<@NotBlank @Length(max = 50) String> events;
}
```

Насчет конфигов:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL81Dialect
```

Использовалась _PostgreSQL_. Для работы с ней потребуется скачать драйвер.

В конфигах мы задаем ссылку на БД, указываем имя пользователя и пароль от БД, способ инициализации базы данных, диалект
БД.

Для работы с БД также потребуется создать интерфейс репозитория, который будет расширять интерфейс другого репозитория.
В данном случае -_CrudRepository_.

```java

@Repository
public interface MyRepository extends CrudRepository<ToDoList, Long> {
}
```

Остальную работу сделает _Spring_.

## Вывод программы

### Добавление списка дел в БД

![](C:\Users\Dan\Desktop\todolist_POST.png)

### Получение всех списков дел из БД

![](C:\Users\Dan\Desktop\todolist_GET.png)

### Непрошедший валидацию запрос

![](C:\Users\Dan\Desktop\todolist_POST_INVALID.png)