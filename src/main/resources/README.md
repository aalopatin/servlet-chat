### Создание БД:

Для запуска программы необходимо создать базу данных с таблицей message.

Привет запроса SQL для создания таблицы на PostgreSQL следующий:

```sql
-- Table: public.message

-- DROP TABLE public.message;

CREATE TABLE public.message
(
usr character(25) COLLATE pg_catalog."default",
message character(1000) COLLATE pg_catalog."default",
datetime timestamp without time zone
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.message
OWNER to postgres;
```

### Файл настроек:

Для подключения к базе данных необходимо положить в каталог WEB-сервера файл application.properties, следующего содержания:

```properties
jdbc.drivers=org.postgresql.Driver
jdbc.url=jdbc:postgresql:chat
jdbc.username=username
jdbc.password=password
```

Параметры необходимо заполнить своими значениями, для подключения к БД.

### Блокировка пользователей:
Для проверки функции блокировки пользователей надо перейти по адресу /admin.
Там надо заполнить два поля: тип блокировки и имя пользователя и нажать кнопку для отправки данных.

_не стал реализовавывать проверку по пользователю, по идеи надо сделать проверку, что текущий пользовать admin, 
но так как это элементарное приложение, которое не предусмтаривает какие-либо пароли и разграничения доступа,
не было смысла, добавлять проверку на администратора, т.е. любой пользователь может задать в качестве своего имени admin._

