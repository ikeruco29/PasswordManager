FROM mysql

ENV MYSQL_ROOT_PASSWORD passwd

ADD sql_scripts/ddbb_init.sql /docker-entrypoint-initdb.d

EXPOSE 3306