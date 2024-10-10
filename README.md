__README__
==========

Данное приложение предназначено для чтения и обработки данных из файлов .xlsx.
Использованный стек: Java SE 17, Spring Boot 3.3.4, Maven, Lombok, REST-архитектура, Swagger, Apache POI.

Установка и запуск:
-----------------------------------

Склонируйте репозиторий.

На устройстве должна быть установлена Java. В директории ```XlsxReader``` склонированного репозитория откройте GitBash и выполните команду:

```./mvnw clean package```

В директории ```XlsxReader\target``` появится jar файл. Откройте GitBash в этой директории и выполните команду для запуска приложения:

```java -jar [имя jar файла]```

Пример:

```java -jar XlsxReader-1.0.0-SNAPSHOT.jar```

Работа с приложением:
---------------------------------------

После запуска приложение будет доступно по адресу: http://localhost:8044

Приложение поддерживает Swagger. Получить доступ к Swagger UI можно по адресу:  http://localhost:8044/swagger-ui.html.

Документация содержится в Swagger.

Функционал приложения:
------------------------------------------

### Контроллер ExcelController

Приложение читает список чисел из .xlsx файла с помощью Apache POI и выдает максимальное число, порядковый номер которого запрашивается в запросе. 

**Метод getAllEntries** - принимает запросы POST на:

```http://localhost:8044/api/excel-reader/find-max-number```

В теле запроса нужно указать полный путь к файлу .xlsx и желаемый порядковый номер.

Файл должен содержать список чисел в первой колонке первого листа начиная с ячейки А1. 

Метод возвращает JSON с кодом ```200``` и числом-результатом.

Метод вернет сообщение с кодом ```400``` и описание ошибки, если:
1) такого порядкового номера не может быть в списке чисел;
2) в первой колонке первого листа начиная с ячейки А1 нет числа;
3) по указанному пути нет файла .xlsx.
