#!/bin/bash


containerName=$1
dockerImageTag=$2  
docker ps -f name=$containerName | grep -w $containerName

if [ $0 -eq 0 ];then
	docker kill $containerName > /dev/null 2>&1
	docker rm $containerName > /dev/null 2>&1
	docker run -d $dockerImageTag --name api-reservation
else
	docker run -d $dockerImageTag --name api-reservation
fi