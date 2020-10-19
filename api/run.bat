@echo off
set POSTGRESQL_USER=postgres
set POSTGRESQL_PASSWORD=postgres
set POSTGRESQL_DATABASE=crudapi
set POSTGRESQL_SERVICE_PORT=5432
set POSTGRESQL_SERVICE_HOST=localhost
call mvn package -Pwildfly-jar
call java -jar target\api-bootable.jar
