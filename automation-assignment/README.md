# automation-assignment

## Pre Requisite
1. Java 8 or higher
2. Gradle
3. TestNG

##  Test Data
Test Data is not hardcoded anywhere in the code. Project using Faker Java API to generate test data.

## Test Locations
- Web Tests Location: src/test/java/com/herokuapp/web/tests/CreateUserWebTest.java
- API Tests Location: src/test/java/com/herokuapp/api/tests/CreateUserAPITest.java

## Run Test in Local
Note - make sure Gradle is setup in you system if running locally

Command For Web Tests-
- gradle test -Pweb -Dbrowser=chrome -Denv=dev

note - change your browser and env to others as needed. Default browser is Chrome and env is DEV.

Command For API Tests-
- gradle test -Papi -Denv=dev

- note - change env to others as needed. Default env is DEV.

For both Web & API
- gradle test -Pweb-api -Dbrowser=chrome -Denv=dev

## Run Test in GitLab
1. Upload this project to GitLab
2. Go to Build option from the Left menus and select Pipeline
3. Pipeline options should display (check the attached image 'gitlab-pipeline-options.png' in root of the project)
4. Select the options and run Pipeline
