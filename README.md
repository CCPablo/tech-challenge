# Reto: Microservicio para el consumo de tweets

## Historias de usuario
* Filtrar tweets cuyos usuarios superen un número N de seguidores (default 1500) y cuyo idioma esté en una lista de idiomas permitidos (default español, francés, italiano).
* Persistir tweets en base de datos. Deben almacenarse los siguientes datos: usuario, texto, localización, validación.
* Definir API REST que permita:
  * Consultar los tweets.
  * Marcar un tweet como validado.
  * Consultar los tweets validados por usuario.
  * Consultar una clasificación de los N hashtags más usados (default 10).

## Consideraciones

* Utilizar Spring Boot para la implementación.
* Realizar la persistencia en memoria.

## Implementación

* Para la persistencia de datos se ha definido una base de datos MYSQL, haciendo uso de JPA. La creación, tanto de la base de datos como de las tablas, es generada automáticamente en un servidor mysql que ha de estar corriendo en la máquina con usuario y contraseña root, root:

```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/tweets_pcc?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
```

![db diagram](https://i.ibb.co/r58DgbF/DB-Diagram.png)

* Se han realizado algunos tests, aunque faltarían algunos para asegurar el funcionamiento completo del servicio.
* Las API Keys para Twitter API están preparadas para ser incluidas en twitter4j.properties

## Requisitos

A continuación se enumeran las versiones utilizadas para ejecutar el proyecto:

* Java 16
* Apache Maven 3.8.1
* MYSQL 8.0.25