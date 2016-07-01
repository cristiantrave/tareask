# tareask

Tareask is an internal task manager for companies. He'll help you get better productivity and efficiency.

## Requirements

### Maven web application project

I used the Netbeans IDE to create the project:
```
New project > Maven web application (with Glassfish server)
```

We will need to add Hibernate and Spring MVC to our project maven:
```
Project properties > Frameworks > Add > Select Hibernate and Spring MVC
```

You need to add template system thymeleaf dependencies in pom.xml:
```
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf-spring4</artifactId>
    <version>2.1.4.RELEASE</version>
</dependency>  
<dependency>
    <groupId>nz.net.ultraq.thymeleaf</groupId>
    <artifactId>thymeleaf-layout-dialect</artifactId>
    <version>1.4.0</version>
</dependency>  
```

## References to documentation

### Netbeans IDE
- https://netbeans.org/

### Hibernate ORM
- http://hibernate.org/

### Thymeleaf: template engine
- http://www.thymeleaf.org/
