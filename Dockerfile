# FROM eclipse-temurin:17-jdk

# WORKDIR /app

# COPY target/ms-alertas-0.0.1-SNAPSHOT.jar app.jar

# EXPOSE 8080

# ENTRYPOINT ["java","-jar","app.jar"]

# =========================
# ETAPA 1: BUILD (Maven)
# =========================
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiamos todo el proyecto
COPY . .

# Compilamos el jar dentro del contenedor
RUN mvn clean package -DskipTests


# =========================
# ETAPA 2: RUNTIME (ligero)
# =========================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiamos SOLO el jar generado
COPY --from=builder /app/target/ms-alertas-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]