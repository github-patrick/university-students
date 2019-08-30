#!/bin/sh

set -e
cd university-resource

mvn clean install -Dspring.profiles.active=dev

cd ../

cp -R university-resource/* built-university-resource