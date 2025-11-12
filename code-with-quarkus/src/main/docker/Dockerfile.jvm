### ESTÁGIO 1: Build com Maven
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copia arquivos de configuração do Maven
COPY code-with-quarkus/pom.xml .
COPY code-with-quarkus/.mvn .mvn
COPY code-with-quarkus/mvnw .
COPY code-with-quarkus/mvnw.cmd .

# Baixa dependências para cache
RUN mvn dependency:go-offline -B

# Copia o código-fonte
COPY code-with-quarkus/src ./src

# Compila o projeto e cria o fast-jar
RUN mvn package -B -Dquarkus.package.type=fast-jar -DskipTests

### ESTÁGIO 2: Imagem final de execução
FROM registry.access.redhat.com/ubi9/openjdk-21:1.23
USER 185
WORKDIR /deployments

# Copia os artefatos compilados
COPY --from=build --chown=185:0 /app/target/quarkus-app/ .

# Expõe a porta
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java","-jar","quarkus-run.jar"]
