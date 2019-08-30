#!/bin/sh
 set -e

echo publishing the image..........
ls -l university-resource

cd university-resource
mvn clean install -DskipTests