# spring-boot-3-api

Spring boot 3 api

# Requirements 

* Java 17
* Gradle version 8.3 
* Intellij 

# Setup :

* Clone the repository.
* Open the project in Intellij.
* Run the project.

# Running unit tests

* Inside a console with Java 17 and Gradle 8.3 installed, run the following command:
* `gradle test`
* 
# Running the app :
* Inside a console with Java 17 and Gradle 8.3 installed, run the following command:
* `gradle bootRun`

# Swagger
The swagger is running in the following url :
http://localhost:8080/swagger-ui/index.html

# Use cases :

* Get the cellphone info :
  * `curl --location --request GET 'http://localhost:8080/v1/cellphone?name=Samsung Galaxy S9' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "phoneName":"IPhone",
    "username":"Juan"
    }'`

* Creating a new booking :
  * POST `curl --location --request POST 'http://localhost:8080/v1/booking' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "phoneName":"Samsung Galaxy S9",
    "username":"Juan"
    }'`
  
* Return the cellphone : 
  * curl --location --request ```curl --location --request POST 'http://localhost:8080/v1/cellphonereturn' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "phoneName":"Samsung Galaxy S9",
    "user":"Juan"
    }'```

# Valid names to for the cellphones

    S9("Samsung Galaxy S9"),
    S8("Samsung Galaxy S8"),
    NEXUS_6("Motorola Nexus 6"),
    ONEPLUS_9("Oneplus 9"),
    IPHONE_13("Apple iPhone 13"),
    IPHONE_12("Apple iPhone 12"),
    IPHONE_11("Apple iPhone 11"),
    IPHONE_X("iPhone X"),
    NOKIA_3310("Nokia 3310");