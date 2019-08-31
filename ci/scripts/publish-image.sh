#!/bin/sh
 set -e

echo publishing the image..........
ls -l university-resource

cd university-resource
mvn clean install -DskipTests

cd ../

echo "start to copy files to the folder built-university-resource"
cp -R university-resource/* built-university-resource
echo "finished copying files"
ls built-university-resource