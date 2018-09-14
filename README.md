# Real-time-fitness-tracking-system


## Overview:
This is a web app that designed and implemented to track registered users's information when they are jogging outdoor. The health center (the monitor) can track the runner's location, 
heart rate(if the runner wears a sport bracelet or smart watch), speed, etc. If the runner needs help, the health center can show the nearest help center on the map.

Notice:
This app is still in process. The backend services almost complete. The raw data now can rendered on screen in real time. I need to add Google Map API for Java to the frontend
so that the raw data can be shown in Google map.

#### --check demo file for more detail--

### Main frameworks and tools used:
Backend:
Java, Spring, Spring MVC, Spring Boot, Spring Data, Spring Cloud, Netflix OSS, SQL, JPA, Hibernate, Maven, Tomcat, RabbitMQ, WebSocket, REST

Database:
MySQL, MongoDB, H2

Frontend:
HTML, JavaScript, Bootstrap

Tools:
Git, Docker, IntelliJ IDEA

## to start the app with exsiting user simulation data :
1.If you don't want t re-build the app, skip to step 2.
To re-build the app, in main folder, run:
$cd spring-cloud-nike-running
$mvn clean install

2. we use Docker as the container for our database
To start Docker:
```
$cd spring-cloud-nike-running
#docker-compose up
rabbitMQ UI: 
```
http://localhost:15672/#/

3. to start each microservice:
(todo: need a script to do this step):

a). start eureka (service regestation and discovery): 
```
$cd spring-cloud-nike-running/platform/eureka/target
$java -jar running-eureka-server-0.0.1-SNAPSHOT.jar
```
http://localhost:8761/ for UI

b). start hystrix (circuit breaker):
```
$cd spring-cloud-nike-running/platform/hystrix-dashboard/target
$java -jar running-hystrix-dashboard-0.0.1.BUILD-SNAPSHOT.jar
```
http://localhost:7979/hystrix for UI
type : "http://localhost:9005/hystrix.stream" ,  click monitor stream

c). three service:
```
$cd spring-cloud-nike-running/running-location-simulator/target
$java -jar running-location-simulator-1.0.0.BUILD-SNAPSHOT.jar

$cd spring-cloud-nike-running/running-location-distribution/target
$java -jar running-location-distribution-1.0.0.BUILD-SNAPSHOT.jar

$cd spring-cloud-nike-running/running-location-updator/target
$java -jar running-location-distribution-1.0.0.BUILD-SNAPSHOT.jar
```
d). app html page (implemented in updator. it will be rendered while updator is running):
http://localhost:9007

d) start the app:
 int postman:
Start the app:
set a "GET" request to: http://localhost:9005/api/simulation 

Pause the app:
set a "GET" request to: http://localhost:9005/api/cancel
