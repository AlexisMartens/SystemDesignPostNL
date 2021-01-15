#! /bin/bash
 
declare current="$(pwd)"
 
docker login
 
cd ~/Documents/SystemDesignPostNL/BestelManagement
docker build -t alexismartens72/bestelmanagement:v1 . && docker push alexismartens72/bestelmanagement:v1
cd ../ExterneLeveringService
docker build -t alexismartens72/externeleveringservice:v1 . && docker push alexismartens72/externeleveringservice:v1
cd ../TrackAndTraceService
docker build -t alexismartens72/trackandtraceservice:v1 . && docker push alexismartens72/trackandtraceservice:v1
cd $current 
