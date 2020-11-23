echo ++++++++++++++++++++++++++++++++++++++
rmdir /s/q target
rem pause

echo ++++++++++++++++++++++++++++++++++++++
cmd /c mvnw -ntp -Pprod verify jib:dockerBuild

docker image ls
rem pause

echo ++++++++++++++++++++++++++++++++++++++
docker image tag jhipstersimple jhuang64/jhipstersimple
docker image tag jhipstersimple u20041-kworker3-dev:5000/jhipstersimple
docker push jhuang64/jhipstersimple
docker push u20041-kworker3-dev:5000/jhipstersimple

docker image ls
