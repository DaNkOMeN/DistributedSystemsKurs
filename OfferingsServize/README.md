Quarkus guide: https://quarkus.io/guides/rest-json

����� ����������� ������ 
mvn install

����� ���������

mvn quarkus:dev

����� ������� ���� ��� �������

docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name offering_service_db -e POSTGRES_USER=offering_admin -e POSTGRES_PASSWORD=offering_admin -e POSTGRES_DB=offering_service_db -p 7777:5432 postgres:10.5