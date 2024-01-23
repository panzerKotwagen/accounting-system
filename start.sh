#!/bin/bash

cp -f ./target/accounting-system-0.0.1-SNAPSHOT.jar ./docker/app/accounting-system-0.0.1-SNAPSHOT.jar

# Ensure, that docker-compose stopped
docker-compose stop

# Start new deployment
docker-compose up --build -d