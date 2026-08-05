./mvnw clean compile

docker-compose -f assets/local/docker-compose/docker-compose.yaml down
docker-compose -f assets/local/docker-compose/docker-compose.yaml up

./mvnw spring-boot:run