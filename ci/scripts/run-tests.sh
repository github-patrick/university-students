#!/usr/bin/env bash

source /docker-lib.sh
start_docker

cd university-resource
mvn verify -Plocal-e2e

