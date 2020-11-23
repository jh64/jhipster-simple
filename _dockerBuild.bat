goto next
:next

echo ++++++++++
cmd /c mvnw -ntp -Pprod verify jib:dockerBuild
pause

echo ++++++++++
docker image tag jhipstersimple jhuang64/jhipstersimple
docker push jhuang64/jhipstersimple

echo ++++++++++
docker image ls
