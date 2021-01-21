Quarkus guide: https://quarkus.io/guides/rest-json

Чтобы заинсталить проект 
mvn install

Чтобы запустить

mvn quarkus:dev

Чтобы поднять базу для сервиса

docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name price_list_db -e POSTGRES_USER=price_admin -e POSTGRES_PASSWORD=price_admin -e POSTGRES_DB=price_list_db -p 5432:5432 postgres:10.5