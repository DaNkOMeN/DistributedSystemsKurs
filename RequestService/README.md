Quarkus guide: https://quarkus.io/guides/rest-json

docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name request_db -e POSTGRES_USER=request_admin -e POSTGRES_PASSWORD=request_admin -e POSTGRES_DB=request_db -p 9999:5432 postgres:10.5