FROM mysql:8.0.22

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=ecommecer

COPY ./init_db.sql /docker-entrypoint-initdb.d/


CMD ["mysqld", "--default-authentication-plugin=mysql_native_password"]
