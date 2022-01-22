#!/bin/bash

if [ "$#" -lt 1 ]; then
  echo "$# is Illegal number of parameters."
  echo "Usage: 1st parameter [ docker img version ]."
  exit 1
fi

./gradlew bootJar

docker build -t shiroed1211/example-spring-backend:$1 .
docker push shiroed1211/example-spring-backend:$1