#!/bin/sh

set -e
cd university-resource

mvn clean install -Dspring.profiles.active=dev -DskipTests

cp -R target/*.jar ../artifacts/