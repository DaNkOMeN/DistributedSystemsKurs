Quarkus guide: https://quarkus.io/guides/rest-json

docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name event_db -e POSTGRES_USER=event_admin -e POSTGRES_PASSWORD=event_admin -e POSTGRES_DB=event_db -p 8888:5432 postgres:10.5