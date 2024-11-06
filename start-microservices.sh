#!/bin/bash

# Navigate to each microservice's directory and run it
# Adjust the paths to your actual microservice locations
cd /home/malise/Engineer/springboot/job_microservice/reviews && mvn spring-boot:run &

echo "Reviews microservices is running!"
echo "====================================================================================================================================="
echo "==============================Starting job service==================================================================================="
cd /home/malise/Engineer/springboot/job_microservice/job && mvn spring-boot:run &
echo "Job microservices is running!"
echo "====================================================================================================================================="
echo "=================================Starting company service================================================================================"
cd /home/malise/Engineer/springboot/job_microservice/company && mvn spring-boot:run &
echo "Company microservices is running!"
echo "====================================================================================================================================="
echo "====================================================================================================================================="

# Wait for all background processes to finish
wait

echo "All microservices are running!"

