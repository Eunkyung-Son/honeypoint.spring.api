set -e
VERSION='1.0.1'
echo ${VERSION}
rm *.jar  || true
cd ../
gradle build -x test --warning-mode=all


cd docker
cp ../build/libs/*.jar .
docker rmi honeypoint:latest || true
docker build -t  honeypoint:${VERSION} .
date
echo ${VERSION}

