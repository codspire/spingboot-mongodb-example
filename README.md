# spingboot-mongodb-example
Simple implementation of Spring Boot with MongoDB

### Start Local MongoDB
```
"c:\tools\mongodb-win32-x86_64-2012plus-4.2.6\bin\mongod.exe" --dbpath="C:\dev\mongo_data\db1"
```

### Get All Speakers
```
curl http://localhost:8080/speakers | json_pp
```

### Get A Speaker
```
curl http://localhost:8080/speakers/5eae3b07577fe70c71942b87 | json_pp
```

### Create a Speaker
```
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"firstName":"Rakesh","lastName":"Nagar","email":"test@test.com","twitter":"@test"}' \
  http://localhost:8080/speakers
```
