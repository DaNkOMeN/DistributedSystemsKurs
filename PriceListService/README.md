Quarkus guide: https://quarkus.io/guides/rest-json

����� ����������� ������ 
mvn install

����� ���������

mvn quarkus:dev

����� ������� ���� ��� �������

docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name price_list_db -e POSTGRES_USER=price_admin -e POSTGRES_PASSWORD=price_admin -e POSTGRES_DB=price_list_db -p 5432:5432 postgres:10.5