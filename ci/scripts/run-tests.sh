#!/bin/sh

set -e
cd university-resource
mvn verify -Plocal-e2e
