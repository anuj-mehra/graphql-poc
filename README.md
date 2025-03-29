##For Local;
mvn spring-boot:run -Dspring-boot.run.profiles=local

##For higher environments;
java -jar myapp.jar --spring.profiles.active=prod

##Run the tests;
mvn test