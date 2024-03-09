# Use latest MySQL image
FROM mysql
# Set working directory
WORKDIR /tmp
# Copy SQL file(s) to working directory
COPY sql_files/*.sql /tmp/

# Copy main SQL file to docker-entrypoint-initdb.d
# (Specific to MySQL)
COPY sql_files/world.sql /docker-entrypoint-initdb.d/
# Set root password
ENV MYSQL_ROOT_PASSWORD password