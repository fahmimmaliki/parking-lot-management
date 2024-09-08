#!/bin/bash

# Set the Docker image name and tag
IMAGE_NAME=fahmimahmud/parking-lot-management
IMAGE_TAG=latest

# Set the directory to change into
DIRECTORY=/path/to/parking-lot-management

# Pull the Docker image
docker pull ${IMAGE_NAME}:${IMAGE_TAG}

# Change into the specified directory
cd ${DIRECTORY}

# Run Docker Compose to deploy the application
docker compose up -d

# Print a success message
echo "Deployment successful!"
