# A simple API for fetching and saving JSONPlaceholder posts

## Usage

1. Build
```docker
docker build --tag=<image tag> ./
docker run -it -d --name <container name> <image tag> /bin/bash
docker exec -it <container name> /bin/bash
```
2. Run
```bash
sbt run
```
After that, choose:
* **1** for download all the posts to src/main/resources
* **2** for remove all the files from src/main/resources

## Used technologies:
* scala:
  * akka actors
  * akka testkit
  * scalatest
  * sbt
* docker

## Requirements
* Docker >= 19.03.1
