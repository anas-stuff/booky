FROM postgres:latest

# Set environment variables for PostgreSQL
ENV POSTGRES_DB=booky_db
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=password

# Expose the PostgreSQL port
EXPOSE 5432
