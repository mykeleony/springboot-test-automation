<h1 align="center">
  StarWars Planet API
</h1>

This is a project that develops and tests an API to manage planets from the Star Wars franchise. The focus of the project is to ensure that the API is functioning correctly through a wide range of tests. The project uses various tools to ensure test quality, including Spring Boot Test, Junit 5, Mockito, AssertJ, Hamcrest, JsonPath, Jacoco, and Pitest.

## ✨ Technologies

- [Java](https://www.oracle.com/java/technologies/downloads/)
- [MySql](https://dev.mysql.com/downloads/mysql/)
- [Maven](https://maven.apache.org/download.cgi)
- [SpringBoot](https://spring.io/projects/spring-boot)
- [Spring Testing](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#testing-introduction)
- [JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito](https://site.mockito.org)
- [AssertJ](https://github.com/assertj/assertj)
- [Hamcrest](http://hamcrest.org/JavaHamcrest/)
- [Jacoco](https://github.com/jacoco/jacoco)
- [Pitest](https://pitest.org)

<br>

<p align="center">
  <img alt="License" src="https://img.shields.io/static/v1?label=License&message=MIT&color=8257E5&labelColor=000000"> <br>
  <img src="https://img.shields.io/static/v1?label=Udemy course&message=Automated Tests in Practice with Spring Boot&color=8257E5&labelColor=000000" alt="Automated Tests in Practice with Spring Boot" />
</p>


## 💻 Project

This project is a web service that provides data about the Star Wars franchise, more specifically about the planets shown in the movies.

It was developed during the course [Automated Tests in Practice with Spring Boot](https://www.udemy.com/course/testes-automatizados-na-pratica-com-spring-boot/?referralCode=7F6C5AA14AE558497FE0), having focus on automated tests creation.

## 🛠️ Configuration

The project requires a MySQL database, so it is necessary to create a database with the following commands:

```
$ sudo mysql

CREATE USER 'user'@'%' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON *.* TO 'user'@'%' WITH GRANT OPTION;

exit

$ mysql -u user -p

CREATE DATABASE starwars;

exit
```

During the tests, the database tables will be created automatically.

## 🚀 Build & Run

To run the project locally, follow these steps:

1. Ensure you have JDK 11 or higher installed on your machine;
2. Clone the repository on your local machine;
3. Open the terminal in the root folder of the project;
4. Run the following command to compile and run the project:

```sh
$ ./mvnw clean verify
```
