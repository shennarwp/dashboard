# Stage that builds the application, a prerequisite for the running stage
FROM maven:3.6.3-openjdk-15-slim as build
RUN curl -sL https://deb.nodesource.com/setup_12.x | bash -
RUN apt-get update -qq && apt-get install -qq --no-install-recommends nodejs

# Stop running as root at this point
RUN useradd -m myuser
WORKDIR /usr/src/app/
RUN chown myuser:myuser /usr/src/app/
USER myuser

# Copy pom.xml and prefetch dependencies so a repeated build can continue from the next step with existing dependencies
COPY --chown=myuser pom.xml ./
# RUN mvn dependency:go-offline -Pproduction

# Copy all needed project files to a folder
COPY --chown=myuser:myuser src src
COPY --chown=myuser:myuser frontend frontend
COPY --chown=myuser:myuser package.json pnpm-lock.yaml webpack.config.js ./


# Build the production package, assuming that we validated the version before so no need for running tests again
RUN mvn clean package -Pproduction

# Running stage: the part that is used for running the application
FROM adoptopenjdk/openjdk15:alpine-jre
COPY --from=build /usr/src/app/target/*.jar /usr/app/app.jar
RUN addgroup -S dashboard && adduser -S user -G dashboard
# RUN useradd -m myuser
USER user
EXPOSE 8080
CMD java -jar /usr/app/app.jar
