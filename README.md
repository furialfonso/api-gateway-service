# API-GATEWAY-SERVICE

Application created in Spring Boot and composed of Spring Cloud Gateway. This app is in charge for authorization.

### Author
  - *Andres Felipe Alfonso Ortiz*

#### Technologies
  - *Java*: programming language.
  - *Spring Boot*: framework.
  - *Spring Cloud Gateway*: Dependenci for service api gateway.
  - *Docker*: application's contenerization.
  - *Cors Config*: Origin Resource Sharing (CORS) in the response.

### Recomendation
  - You have should 1 cors config in the all project.

## Steps
  1. create jar
   ``mvn clean install -DskipTests``
  2. creatte image and deploy
    ``docker compose up``

#### Spring Cloud Gateway
  - Documentation
    https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/
  - Configuration
    https://www.youtube.com/watch?v=pzxtXifloSE&t=1316s