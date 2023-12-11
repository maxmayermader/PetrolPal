package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
/**
 * server.port=8080
 * spring.mvc.log-request-details=true
 * logging.level.org.springframework.web=DEBUG
 */
/** this is for connecting to the coms309 server
 *http://coms-309-031.class.las.iastate.edu:8080/
 *
 spring.jpa.hibernate.ddl-auto=update
 spring.datasource.url=jdbc:mysql://@coms-309-031.class.las.iastate.edu:3306/new
 spring.datasource.username=username
 spring.datasource.password=password
 spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
 #spring.jpa.show-sql: true
 spring.main.allow-bean-definition-overriding=true

 *
 * These 2 are for connecting locally
 *
 spring.datasource.url=jdbc:h2:mem:testdb
 spring.datasource.driverClassName=org.h2.Driver
 spring.datasource.username=sa
 spring.datasource.password=password
 spring.main.allow-bean-definition-overriding=true

 *
 yml stuff
 * spring:
 *   datasource:
 *     url: jdbc:h2:mem:testdb
 *     driverClassName: org.h2.Driver
 *     username: sa
 *     password: password
 *
 *
 *
 *     ssh iibussan@coms-309-031.class.las.iastate.edu
 */