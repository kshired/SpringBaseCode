#!/bin/bash

if [ "$#" -lt 1 ]; then
  echo "$# is Illegal number of parameters."
  echo "Usage: 1st parameter [ docker img version ]."
  exit 1
fi

docker pull shiroed1211/example-spring-backend:$1
docker stop spring-backend
docker rm spring-backend
docker run -p 8080:8080 --name spring-backend -d shiroed1211/example-spring-backend:$1