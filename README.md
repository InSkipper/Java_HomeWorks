# First Homework
## Задание №1
**JRE и JDK**
1) Установить openjdk.
2) Проверить работу из консоли, выполнив java --version.
3) Приложить скриншот выполнения

![java_version](https://user-images.githubusercontent.com/76143861/160785295-66a4b243-137f-4c0d-9c43-13e631a5bd7a.png)

## Задание №2
**Maven/gradle**
1. Собрать jar-файл, добавив какую-нибудь зависимость и использовав её в коде. Как для maven, так и для gradle.
2. Приложить скриншоты сборки и работающего приложения

В качестве используемой [зависимости](https://github.com/InSkipper/Java_HomeWorks/blob/344363b384a5653f226f78b1111411f361d805df/pom.xml#L26) выступил _lambok_. Использовался он [здесь](https://github.com/InSkipper/Java_HomeWorks/blob/344363b384a5653f226f78b1111411f361d805df/src/main/java/com/example/firsthomework/MyClass.java#L8).

![result](https://user-images.githubusercontent.com/76143861/160787120-11b4945a-194d-486e-b85e-42c1d3fb2ea8.png)

## Задание №3
**Git**
1) Создать проект на GitHub
2) Склонировать проект
3) В отдельной ветке добавить новую фичу (добавить новый файл, например)
4) Слить с основной веткой
5) Залить изменения в удаленный репозиторий

```git
$ git clone https://github.com/InSkipper/Java_HomeWorks.git \\клонируем
$ git chechout -b NewBranch                                 \\создаем и переходим в новую ветку
$ git add .                                                 \\добовляем изменения
$ git commit -m "Initial commit"                            \\коммитим изменения
$ git checkout main                                         \\переходим в основную ветку
$ git merge NewBranch                                       \\сливаем новую ветку с текущей
$ git push                                                  \\заливаем изменения
```
