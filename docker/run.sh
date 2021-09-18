docker rm -f honeypoint
# couchbaseIP=$(docker inspect -f '{{.Name}} - {{.NetworkSettings.IPAddress }}' couchbase  | awk -v N=3 '{print $N}')
docker run -d                                                           \
    --restart=always                                                    \
    --name=honeypoint                                                   \
    -p 8082:8082                                                        \
    -v /Users/seungwon/data:/honeypoint/data                            \
    -e upload.path=/madamfive/data/uploads                              \
    -e spring.http.multipart.max-file-size=1000Mb                       \
    -e spring.http.multipart.max-request-size=1000Mb                    \
    -e management.security.enabled=false                                \
    honeypoint:1.0.1