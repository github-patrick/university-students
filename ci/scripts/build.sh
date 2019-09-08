#!/bin/sh

set -e
cd university-resource

mvn clean install -Dspring.profiles.active=dev

cd ../
echo "start to copy files to the folder built-university-resource"
cp -R university-resource/* artificaft-for-image
echo "finished copying files"
ls artificaft-for-image
