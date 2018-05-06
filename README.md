# bx-spring
Аренда автомобилей(bookingcar)

## Технологии

* [Spring Boot](http://projects.spring.io/spring-boot/)
* [Maven](http://maven.apache.org/)
* [Spring Security](http://projects.spring.io/spring-security/)
* [Spring Security OAuth](http://projects.spring.io/spring-security-oauth/)
* [Spring MVC REST](http://spring.io/guides/gs/rest-service/)
* [Spring Data JPA](http://projects.spring.io/spring-data-jpa/)
* [PostgreSQL](http://www.postgresql.org/) (Production, Development)
* [H2 Database Engine](http://www.h2database.com/) (Test)
* [Flyway Database Migration](http://flywaydb.org/)
* [Docker](https://www.docker.com/)
* [Docker Compose](https://docs.docker.com/compose/)

## Сборка

```
# Build the project
mvn package

# build the images
docker build -t bx-rest bx-rest/.

```

## Запуск

```
# run the containers
docker run -d --name todo-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=todo postgres:9.4.5
docker run -d --name todo-rest --link todo-db:todo-db todo-rest

# or just use docker-compose
# for building and running
docker-compose up

```

