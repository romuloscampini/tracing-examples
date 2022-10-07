#!/bin/bash
set -e

echo "Building module: animal-name-service..."
cd animal-name-service
mvn clean package

echo "Building module: name-generator-service..."
cd ../name-generator-service
mvn clean package

echo "Building module: scientist-name-service..."
cd ../scientist-name-service
mvn clean package
