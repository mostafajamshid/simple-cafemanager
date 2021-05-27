#cafemanagersystem
Created by Mostafa Jamshid on 27.05.2021.
Simple Cafe Manager System 

Technologies;

           * MySQL (5.7.11)
           * Spring Data JPA (Hibernate)
           * Spring Security
           * Spring MVC
           * Spring Boot
           * Thymeleaf
           * Bootstrap (UI Presentation)
           * Admin LTE template
 
BUILD and RUN

    1.Download or clone the project
    2.Run Database.sql -> Create Database with Database.sql
    3.Edit application.properties (it will be enough the replace them)
            spring.datasource.url = jdbc:mysql://localhost:3306/(your database name)
            spring.datasource.username = (your username)
            spring.datasource.password = (your password)
            server.port = 8000 (server port for tomcat - localhost:8000 -> if you want to change server port, edit this line)
    4.Run the project
            * open your browser go to -> http://localhost:8000 and HAVE FUN 
 