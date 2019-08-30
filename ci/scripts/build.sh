#!/bin/sh

set -e
cd university-resource
mvn clean install -Dspring.profiles.active=dev

mkdir built-university-resource

mv target built-university-resource/
mv Dockerfile built-university-resource/
mv docker-compose.yml built-university-resource