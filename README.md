Clone project
```
git clone git@github.com:alvarodr/ecommerce.git
```
Build
```
./gradlew build test
```
Run
```
./gradlew bootRun
```
Documentation API Swagger interface
```
http://localhost:8080/ecommerce/swagger-ui/
```
Curl
```
curl -X GET "http://localhost:8080/ecommerce/product/35455?dateTimeApplied=2020-06-15%2010%3A00" -H "accept: */*" -H "brandId: 1"
```
