set -e
VERSION='1.0.5'
echo ${VERSION}
rm *.jar  || true
cd ../
gradle build -x test --warning-mode=all


cd docker
cp ../build/libs/*.jar .
docker rmi honeypoint:latest || true
docker build -t  honeypoint:${VERSION} .
docker tag honeypoint:${VERSION} sek9510/honeypoint:${VERSION}
docker tag honeypoint:${VERSION} sek9510/honeypoint:latest
docker push sek9510/honeypoint:${VERSION}
date
echo ${VERSION}

