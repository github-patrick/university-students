#!/bin/sh

set -e
cd university-resource
mvn clean install -Dspring.profiles.active=dev

cp -R ./ built-university-resource