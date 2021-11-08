docker rm -f honeypoint
docker run -d 
    -—restart=always 
    -—name=honeypoint 
    -p 8082:8082 
    -e file.path=/data/uploads 
    -v /data/uploads:/honeypoint/data 
    -e management.security.enabled=false 
    sek9510/honeypoint:1.0.2