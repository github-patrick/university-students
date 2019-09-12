#!/bin/sh

set -e
cd university-resource

mvn clean install -Dspring.profiles.active=dev

cd ../

cp -R university-resource/target/university-students-0.0.1-SNAPSHOT.jar artefact-deploy