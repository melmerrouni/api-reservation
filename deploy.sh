#!/bin/bash


containerName=$1
dockerImageTag=$2  
docker ps -f name=$containerName | grep -w $containerName

if [ $? -eq 0 ];then
	docker kill $containerName > /dev/null 2>&1
	docker rm $containerName > /dev/null 2>&1
	docker run --name api-reservation -p 8088:8088 -d $dockerImageTag
else
	docker run --name api-reservation -p 8088:8088 -d $dockerImageTag 
fi
